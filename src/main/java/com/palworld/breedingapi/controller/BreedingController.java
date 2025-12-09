package com.palworld.breedingapi.controller;

import com.palworld.breedingapi.model.BreedingResponse;
import com.palworld.breedingapi.model.Pal;
import com.palworld.breedingapi.service.BreedingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/breeding")
@RequiredArgsConstructor // O Lombok cria o construtor para injetar o Service
public class BreedingController {

    private final BreedingService breedingService;

    @GetMapping("/combinations")
    public ResponseEntity<BreedingResponse> getCombinations(@RequestParam String target){
        try{
            // 1. Busca os detalhes do Pal alvo (para mostrar o power dele na resposta)
            Pal targetPal = breedingService.getPalByName(target);

            // 2. Chama o algoritmo para achar os pais
            var combinations = breedingService.findCombinationsFor(target);

            // 3. Monta o JSON bonito de resposta
            BreedingResponse response = BreedingResponse.builder()
                    .targetPal(targetPal.getName())
                    .targetPower(targetPal.getBreedingPower())
                    .combinationsFound(combinations.size())
                    .combinations(combinations)
                    .build();

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Se o Pal não for encontrado no JSON, retorna erro 404
            return ResponseEntity.notFound().build();
        }
    }
}
