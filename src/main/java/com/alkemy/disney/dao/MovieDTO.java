package com.alkemy.disney.dao;

import com.alkemy.disney.entitys.EntityPersonage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class MovieDTO {
    private Long id;

    private String imagen;

    private String titulo;

    private String fechaCreacion;

    private int calificacion;

   private Long idgenero;

    private List<PersonageDTO> personages;

}
