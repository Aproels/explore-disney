package com.alkemy.disney.entitys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="GENDER")
@Getter
@Setter
@ToString
public class EntityGender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String imagen;

    //@OneToMany(mappedBy = "gender",fetch =  FetchType.LAZY)

    //private List<EntityMovie> movieserie;



    }
