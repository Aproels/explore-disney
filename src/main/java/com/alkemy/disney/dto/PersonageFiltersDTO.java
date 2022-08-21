package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class PersonageFiltersDTO {
    private String name;
    private Long age;
    private Double weight;

    private Set<Long> moviesId;


    public PersonageFiltersDTO(String name, Long age, Double weight, Set<Long> moviesId) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.moviesId = moviesId;

    }

}
