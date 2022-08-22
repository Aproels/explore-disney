package com.alkemy.disney.dto;

import com.alkemy.disney.entitys.EntityGender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

import java.util.Set;

@Getter
@Setter

public class MovieDTO {
    private Long id;

    private String image;

    private String title;

    @Pattern(regexp ="\\d{4}-\\d{2}-\\d{2}" , message= "El formato de fecha debe ser yyyy-MM-dd" )
    private String creationDate;

    private Integer score;

    private Long genderId;

    private EntityGender gender;

    private Set<PersonageDTO> personages= new HashSet<>();



}
