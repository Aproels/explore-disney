package com.alkemy.disney.entitys;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "PERSONAJES")
@Getter
@Setter
@SQLDelete(sql= "Update PERSONAJES SET deleted = true WHERE id=?")//soft delete
@Where(clause= "deleted=false")
public class EntityPersonage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String name;

    private Long age;

    private Double weight;

    private String history;

    private boolean deleted= Boolean.FALSE;
    @ManyToMany(mappedBy = "personages",cascade = CascadeType.MERGE)
    private List<EntityMovie> movies = new ArrayList<>();



}
