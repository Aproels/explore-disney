package com.alkemy.disney.disney.entitys;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "PELICULAS_SERIES")
@Getter
@Setter

public class EntityPeliculaSerie {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String titulo;

    @Column(name="FECHA_CREACION")
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private LocalDate fechaCreacion;

    private int calificacion;

    @Column(name="PERSONAJES_ASOCIADOS")
    private String personajesAsociados;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name= "id_genero", insertable = false, updatable = false)

    private EntityGenero genero;

    @Column(name= "id_genero",nullable = false)
    private Long idgenero;

    @ManyToMany(
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
            })

    @JoinTable(name="PERSONAJE_ENPELICULAS",
                joinColumns = @JoinColumn(name= "ID_PELICULAS"),
                inverseJoinColumns = @JoinColumn(name= "ID_PERSONAJE"))
    private Set<EntityPersonaje> personajes = new HashSet<>();



}
