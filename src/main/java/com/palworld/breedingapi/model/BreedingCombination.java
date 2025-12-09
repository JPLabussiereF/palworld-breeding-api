// Representa o par de pais que vamos sugerir
package com.palworld.breedingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BreedingCombination {
    private String parentA;
    private String parentB;
}
