// Representa cada pal no nosso "banco de dados"
package com.palworld.breedingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pal {
    private String Name;
    private int breedingPower;
}
