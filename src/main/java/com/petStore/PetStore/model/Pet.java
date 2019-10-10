package com.petStore.PetStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    private Integer id;
    private String name;
    private String type;
    private String gender;
    private Double price;
    private String color;

    public Pet(String name, String type, String gender, Double price, String color) {
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.price = price;
        this.color = color;
    }
}
