package com.alkemy.disney.mapper;


import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.PersonageDTO;
import com.alkemy.disney.entitys.EntityMovie;
import com.alkemy.disney.entitys.EntityPersonage;
import com.alkemy.disney.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class MovieMapper {
    @Autowired
    private PersonageMapper personageMapper;
    @Autowired

    private MovieRepository movieRepository;

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public MovieBasicDTO MovieEntityToBasicDto(EntityMovie entity) {
        MovieBasicDTO dto = new MovieBasicDTO();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate());
        return dto;
    }

    public List<MovieBasicDTO> MovieEntitySetToBasicDtoList(Collection<EntityMovie> entities) {
        List<MovieBasicDTO> dtos = new ArrayList<>();
        for (EntityMovie entity : entities) {
            dtos.add(MovieEntityToBasicDto(entity));
        }
        return dtos;
    }


    public EntityMovie MovieDtoToEntity(MovieDTO dto) {
        EntityMovie entityMovie = new EntityMovie();


        entityMovie.setId(dto.getId());
        entityMovie.setTitle(dto.getTitle());
        entityMovie.setImage(dto.getImage());
        entityMovie.setGenderId(dto.getGenderId());
        entityMovie.setCreationDate(LocalDate.parse(dto.getCreationDate(),formatter));
        entityMovie.setScore(dto.getScore());
        entityMovie.setGender(dto.getGender());
        for (PersonageDTO personageDTO : dto.getPersonages()) {
            EntityPersonage personage = personageMapper.PersonageDtoToEntity(personageDTO);

            entityMovie.getPersonages().add(personage);

        }

        return entityMovie;
    }

    public MovieDTO MovieEntityToDto(EntityMovie entityMovie, boolean loadPersonages) {
        MovieDTO dto = new MovieDTO();

        dto.setId(entityMovie.getId());
        dto.setTitle(entityMovie.getTitle());
        dto.setImage(entityMovie.getImage());
        dto.setGenderId(entityMovie.getGenderId());
        dto.setCreationDate((entityMovie.getCreationDate().toString()));
        dto.setScore(entityMovie.getScore());
        dto.setGender(entityMovie.getGender());

     if (loadPersonages) {

            dto.setPersonages(personageMapper.PersonageEntitySetToDtoSet(entityMovie.getPersonages(), false));

        }

        return dto;
    }


    public Set<MovieDTO> MovieEntitySetToDtoSet(Collection<EntityMovie> entities, boolean loadCharacters) {
        Set<MovieDTO> dtos = new HashSet<>();
        for (EntityMovie entity : entities) {
            dtos.add(MovieEntityToDto(entity, loadCharacters));
        }
        return dtos;
    }

   /* public List<MovieDTO> MovieEntitySetToDtoList(Collection<EntityMovie> entities, boolean loadCharacters) {
        List<MovieDTO> dtos = new ArrayList<>();
        for (EntityMovie entity : entities) {
            dtos.add(MovieEntityToDto(entity, loadCharacters));
        }
        return dtos;
    }
    */

}


