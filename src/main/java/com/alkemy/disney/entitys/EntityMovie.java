package com.alkemy.disney.entitys;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name= "MOVIES")
@Getter
@Setter
@SQLDelete(sql= "Update MOVIES SET deleted = true WHERE id=?")//soft delete
@Where(clause= "deleted=false")

public class EntityMovie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String title;

    @Column(name="CREATION_DATE")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creationDate;

    private int score;

    private boolean deleted= Boolean.FALSE;

   // @ManyToOne()
   //@JoinColumn(name= "GENDER_ID",insertable = false,updatable = false)

     @ManyToOne
     @JoinColumn(name = "gender_id", insertable = false,updatable = false)
     private EntityGender gender;

  @Column(name= "gender_id",nullable = false)
   private Long genderId;

    @ManyToMany(
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
            })

    @JoinTable(name="PERSONAGES_MOVIE",
                joinColumns = @JoinColumn(name= "MOVIE_ID"),
                inverseJoinColumns = @JoinColumn(name= "PERSONAGE_ID"))
    private Set<EntityPersonage> personages = new HashSet<>();


    public void removePersonage(EntityPersonage personage){
        this.personages.remove(personage);
    }
    public void addPersonage(EntityPersonage personage){
        this.personages.add(personage);
    }

    @Override
    public String toString() {
        return "EntityMovie{" +
                "creationDate=" + creationDate +
                '}';
    }
}
