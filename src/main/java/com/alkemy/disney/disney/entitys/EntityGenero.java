package com.alkemy.disney.disney.entitys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="GENERO")
@Getter
@Setter

public class EntityGenero {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    private String imagen;




}
