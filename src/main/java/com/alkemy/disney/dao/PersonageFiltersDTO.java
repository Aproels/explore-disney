package com.alkemy.disney.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class PersonageFiltersDTO {
    private String name;
    private Long age;
    private Long weight;

    private Set<Long> movies;
    private String order;

    public PersonageFiltersDTO(String name, Long age, Long weight, Set<Long> movies, String order) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.movies = movies;
        this.order = order;
    }

    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC")== 0;
    }
    public  boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC")== 0;
    }
}
