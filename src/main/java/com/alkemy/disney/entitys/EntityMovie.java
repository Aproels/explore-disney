package com.alkemy.disney.entitys;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name= "PELICULAS")
@Getter
@Setter
@SQLDelete(sql= "Update PELICULAS SET deleted = true WHERE id=?")//soft delete
@Where(clause= "deleted=false")

public class EntityMovie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagen;

    private String titulo;

    @Column(name="FECHA_CREACION")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String fechaCreacion;

    private int calificacion;

    private boolean deleted= Boolean.FALSE;

    @ManyToOne()
   @JoinColumn(name= "id_genero")

    private EntityGender gender;

   // @Column(name= "id_genero",nullable = false)
   private Long idgenero;

    @ManyToMany(
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
            })

    @JoinTable(name="PERSONAJE_EN_PELICULAS",
                joinColumns = @JoinColumn(name= "ID_PELICULAS"),
                inverseJoinColumns = @JoinColumn(name= "ID_PERSONAJE"))
    private List<EntityPersonage> personages = new ArrayList<>();

    public void removePersonage(EntityPersonage personage){
        this.personages.remove(personage);
    }
    public void addPersonage(EntityPersonage personage){
        this.personages.add(personage);
    }



}
