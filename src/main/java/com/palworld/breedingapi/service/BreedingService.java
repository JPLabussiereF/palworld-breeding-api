package com.palworld.breedingapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.palworld.breedingapi.model.BreedingCombination;
import com.palworld.breedingapi.model.Pal;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor // Cria o construtor automaticamente para injeção de dependência
public class BreedingService {

    private final ObjectMapper objectMapper; // Ferramenta do Spring para ler JSON
    private List<Pal> palDatabase = new ArrayList<>();

    @PostConstruct
    public void loadData() {
        try{
            // Lê o arquivo pals.json da pasta resources
            InputStream inputStream = getClass().getResourceAsStream("/pals.json");

            // Converte o JSON diretamente para uma Lista de objetos Pal
            palDatabase = objectMapper.readValue(inputStream, new TypeReference<List<Pal>>() {});

            System.out.println("Dados carregados com sucesso! Total de Pals: " + palDatabase.size());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregador base de dados de Pals: " + e.getMessage());
        }
    }

    public Pal getPalByName(String name) {
        return palDatabase.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pal não encontrado: " + name));
    }

    public List<BreedingCombination> findCombinationsFor(String targetName) {
        Pal targetPal = getPalByName(targetName);
        List<BreedingCombination> validPairs = new ArrayList<>();

        for (int i = 0; i < palDatabase.size(); i++) {
            for (int j = i; j < palDatabase.size(); j++) {
                Pal parentA = palDatabase.get(i);
                Pal parentB = palDatabase.get(j);

                int averagePower = (int) Math.floor(parentA.getBreedingPower() + parentB.getBreedingPower() / 2.0);
                Pal closestChild = findClosestPalToPower(averagePower);

                if (closestChild.getName().equalsIgnoreCase(targetPal.getName())) {
                    validPairs.add(new BreedingCombination(parentA.getName(), parentB.getName()));
                }
            }
        }
        return validPairs;
    }

    private Pal findClosestPalToPower(int targetPower) {
        return palDatabase.stream()
                .min(Comparator.comparingInt(p -> Math.abs(p.getBreedingPower() - targetPower)))
                .orElseThrow();
    }

}
