// É a resposta em JSON que vamos enviar para quem chamar a API
package com.palworld.breedingapi.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BreedingResponse {
    private String targetPal;
    private int targetPower;
    private int combinationsFound;
    private List<BreedingCombination> combinations;
}
