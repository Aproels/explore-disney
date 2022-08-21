package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

import java.util.Set;

@Getter
@Setter
public class PersonageDTO {
    private Long id;

    private String image;

    private String name;

    private Long age;

    private Double weight;

    private String history;

    private Set<MovieDTO> movies= new HashSet<>();
}
