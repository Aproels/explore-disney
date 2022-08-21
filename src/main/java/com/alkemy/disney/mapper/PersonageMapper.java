package com.alkemy.disney.mapper;




import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.PersonageBasicDTO;
import com.alkemy.disney.dto.PersonageDTO;
import com.alkemy.disney.entitys.EntityMovie;
import com.alkemy.disney.entitys.EntityPersonage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PersonageMapper {
    @Autowired
    private MovieMapper movieMapper;




    public PersonageBasicDTO  PersonageEntityToBasicDto (EntityPersonage entity) {
        PersonageBasicDTO dto = new  PersonageBasicDTO();
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());


        return dto;
    }

    public List<PersonageBasicDTO> PersonageEntitySetToBasicDtoList (Collection<EntityPersonage> entities) {
        List<PersonageBasicDTO> DTOs = new ArrayList<>();
        for(EntityPersonage entity : entities) {
            DTOs.add(PersonageEntityToBasicDto(entity));
        }
        return DTOs;
    }



    public EntityPersonage PersonageDtoToEntity(PersonageDTO personageDTO){
        EntityPersonage entityPersonage= new  EntityPersonage();

        entityPersonage.setName(personageDTO.getName());
        entityPersonage.setImage(personageDTO.getImage());
        entityPersonage.setHistory(personageDTO.getHistory());
        entityPersonage.setAge(personageDTO.getAge());
        entityPersonage.setWeight(personageDTO.getWeight());


        for (MovieDTO movieDto : personageDTO.getMovies()){

            EntityMovie movie= movieMapper.MovieDtoToEntity(movieDto);
            entityPersonage.getMovies().add(movie);
        }

        return entityPersonage;
    }


    public PersonageDTO PersonageEntityToDto(EntityPersonage entityPersonage, boolean loadMovies){
        PersonageDTO dto = new PersonageDTO();

        dto.setId(entityPersonage.getId());
        dto.setName(entityPersonage.getName());
        dto.setImage(entityPersonage.getImage());
        dto.setHistory(entityPersonage.getHistory());
        dto.setAge(entityPersonage.getAge());
        dto.setWeight(entityPersonage.getWeight());
        //cargar peliculas que estan linkeadas a personajes

       if(loadMovies){

         dto.setMovies(movieMapper.MovieEntitySetToDtoSet(entityPersonage.getMovies(), false));


       }

        return dto;
    }


    public Set<PersonageDTO> PersonageEntitySetToDtoSet(Collection<EntityPersonage> entities, boolean loadMovies) {
        Set<PersonageDTO> dtos = new HashSet<>();
        for (EntityPersonage entity : entities) {
           dtos.add(PersonageEntityToDto(entity, loadMovies));
        }
        return dtos;
    }


}
