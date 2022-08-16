package com.alkemy.disney.mapper;




import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.PersonageDTO;
import com.alkemy.disney.entitys.EntityPersonage;
import com.alkemy.disney.repository.PersonageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonageMapper {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired

    private PersonageRepository personageRepository;


    public List<PersonageDTO> PersonageEntitySetToDtoList(List<EntityPersonage> entities, boolean loadMovieSeries) {
        List<PersonageDTO> dtos= new ArrayList<>();
        for(EntityPersonage entity : entities){
            dtos.add(this.PersonageEntityToDto(entity, true));
        }
        return dtos;
    }


    public EntityPersonage PersonageDtoToEntity(PersonageDTO personageDTO){
        EntityPersonage entityPersonage= new  EntityPersonage();

        entityPersonage.setNombre(personageDTO.getNombre());
        entityPersonage.setImagen(personageDTO.getImagen());
        entityPersonage.setHistoria(personageDTO.getHistoria());
        entityPersonage.setEdad(personageDTO.getEdad());
        entityPersonage.setPeso(personageDTO.getPeso());
        //entityPersonage.setMovies(personageDTO.getMovies());
        return entityPersonage;
    }
    public PersonageDTO PersonageEntityToDto(EntityPersonage entityPersonage, boolean loadMovies){
        PersonageDTO dto = new PersonageDTO();

        dto.setId(entityPersonage.getId());
        dto.setNombre(entityPersonage.getNombre());
        dto.setImagen(entityPersonage.getImagen());
        dto.setHistoria(entityPersonage.getHistoria());
        dto.setEdad(entityPersonage.getEdad());
        dto.setPeso(entityPersonage.getPeso());
        //cargar peliculas que estan linkeadas a personajes

       if(loadMovies){
         List<MovieDTO> moviesDTO= this.movieMapper.MovieEntitySetToDtoList(entityPersonage.getMovies(), false);
         dto.setMovies(moviesDTO);


       }

        return dto;
    }

    public EntityPersonage PersonageDtoToEntityUpdate(PersonageDTO dto) {
        Optional<EntityPersonage> result= personageRepository.findById(dto.getId());
        if(result.isPresent()){
            EntityPersonage entity= result.get();
            entity.setImagen(dto.getImagen());
            entity.setEdad(dto.getEdad());
            entity.setHistoria(dto.getHistoria());
            entity.setPeso(dto.getPeso());
            entity.setNombre(dto.getNombre());
            entity.setMovies(entity.getMovies());

            return entity;
        }
        return null;
    }
    public List<PersonageDTO> PersonageEntityListToDtoList(List<EntityPersonage> entities,boolean loadMovies) {

        List<PersonageDTO> dtos= new ArrayList<>();
        for(EntityPersonage entity : entities){

            dtos.add(PersonageEntityToDto(entity,loadMovies));
        }
        return dtos;
    }
    public List<EntityPersonage> PersonageDtoListToEntity(List<PersonageDTO> dtos){
        List<EntityPersonage> entities= new ArrayList<>();
        for(PersonageDTO dto: dtos){
            entities.add(this.PersonageDtoToEntity(dto));
        }
        return entities;
    }



}
