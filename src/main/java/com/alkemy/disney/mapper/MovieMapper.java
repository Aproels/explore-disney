package com.alkemy.disney.mapper;


import com.alkemy.disney.dao.MovieBasicDTO;
import com.alkemy.disney.dao.MovieDTO;
import com.alkemy.disney.dao.PersonageDTO;
import com.alkemy.disney.entitys.EntityMovie;
import com.alkemy.disney.entitys.EntityPersonage;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.repository.PersonageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieMapper {
    @Autowired
    private PersonageMapper personageMapper;
    @Autowired

    private MovieRepository movieRepository;


    public List<MovieDTO> MovieEntitySetToDtoList(List<EntityMovie> entities, boolean loadMovieSeries) {
        List<MovieDTO> dtos= new ArrayList<>();
        for(EntityMovie entity : entities){
            dtos.add(this.MovieEntityToDto(entity, true));
        }
        return dtos;
    }


    public EntityMovie MovieDtoToEntity(MovieDTO movieDTO){
        EntityMovie entityMovie= new  EntityMovie();

        entityMovie.setFechaCreacion(movieDTO.getFechaCreacion());
        entityMovie.setImagen(movieDTO.getImagen());
        entityMovie.setTitulo(movieDTO.getTitulo());
        entityMovie.setCalificacion(movieDTO.getCalificacion());
        entityMovie.setIdgenero(movieDTO.getIdgenero());
        //entityPersonage.setMovies(personageDTO.getMovies());
        return entityMovie;
    }
    public MovieDTO MovieEntityToDto(EntityMovie entityMovie, boolean loadPersonages){
        MovieDTO dto = new MovieDTO();

        dto.setId(entityMovie.getId());
        dto.setTitulo(entityMovie.getTitulo());
        dto.setImagen(entityMovie.getImagen());
        dto.setIdgenero(entityMovie.getIdgenero());
        dto.setFechaCreacion(entityMovie.getFechaCreacion().toString());
        dto.setCalificacion(entityMovie.getCalificacion());
        //cargar peliculas que estan linkeadas a personajes

        if(loadPersonages){
        List<PersonageDTO> personageDTO= this.personageMapper.PersonageEntityListToDtoList(entityMovie.getPersonages(), false);
        dto.setPersonages(personageDTO);


        }

        return dto;
    }
    public List<MovieDTO> MovieEntityListToDtoList(List<EntityMovie> entities,boolean loadPersonages) {

        List<MovieDTO> dtos= new ArrayList<>();
        for(EntityMovie entity : entities){
            dtos.add(MovieEntityToDto(entity,loadPersonages));
        }
        return dtos;
    }
    public EntityMovie MovieDtoToEntityUpdate(MovieDTO dto) {
        Optional<EntityMovie> result= movieRepository.findById(dto.getId());
        if(result.isPresent()){
            EntityMovie entity= result.get();
            entity.setImagen(dto.getImagen());
            entity.setIdgenero(dto.getIdgenero());
            entity.setTitulo(dto.getTitulo());
            entity.setFechaCreacion(dto.getFechaCreacion());
            entity.setCalificacion(dto.getCalificacion());
            entity.setPersonages(entity.getPersonages());

            return entity;
        }
        return null;
    }
    private LocalDate StringToLocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date =LocalDate.parse(stringDate,formatter);
        return date;

    }

    public List<MovieBasicDTO> MovieEntitySetToBasicDtoList(List<EntityMovie> entities) {

        return null;
    }
}
