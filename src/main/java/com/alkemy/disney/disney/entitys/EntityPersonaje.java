package com.alkemy.disney.disney.entitys;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "PERSONAJES")
@Getter
@Setter


public class EntityPersonaje {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String nombre;

    private int edad;

    private Double peso;

    private String historia;

    @ManyToMany(mappedBy = "personajes",cascade = CascadeType.ALL)
    private List<EntityPeliculaSerie> peliculas= new ArrayList<>();



}
