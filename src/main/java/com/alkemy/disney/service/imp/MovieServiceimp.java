package com.alkemy.disney.service.imp;


import com.alkemy.disney.dao.MovieBasicDTO;
import com.alkemy.disney.dao.MovieDTO;
import com.alkemy.disney.dao.MovieFiltersDTO;
import com.alkemy.disney.entitys.EntityMovie;
import com.alkemy.disney.entitys.EntityPersonage;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.repository.specifications.MovieSpecification;
import com.alkemy.disney.service.MovieService;
import com.alkemy.disney.service.PersonageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class MovieServiceimp implements MovieService {

    @Autowired
    private PersonageService personageService;

    @Autowired
    private MovieSpecification movieSpecification;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired

    private MovieRepository movieRepository;

    public MovieDTO getDetailsById(Long id){
        Optional<EntityMovie> entity=this.movieRepository.findById(id);
        if(entity.isPresent()){
            MovieDTO dto= this.movieMapper.MovieEntityToDto(entity.get(),true);
            return dto;
        }
        return null;
    }

    public List<MovieDTO> getByFilters( String title, String creationDate, String gender, String order){
        MovieFiltersDTO filtersDTO= new MovieFiltersDTO(title, creationDate, gender,order);
        List<EntityMovie> entities = this.movieRepository.findAll(this.movieSpecification.getByFilters(filtersDTO));
        List<MovieDTO> dtos= this.movieMapper.MovieEntitySetToDtoList(entities, false);

        return dtos;

    }



    public MovieDTO save(MovieDTO movieDTO){

        EntityMovie entity = movieMapper.MovieDtoToEntity(movieDTO);

        EntityMovie entitySaved= movieRepository.save(entity);

        MovieDTO result= movieMapper.MovieEntityToDto(entitySaved,true);

        return result;
    }



    public void delete(Long id){

        movieRepository.deleteById(id);
    }


    public List<MovieBasicDTO> getAllMovie() {

        List<EntityMovie> entities = movieRepository.findAll();
        List<MovieBasicDTO> resultBasic= movieMapper.MovieEntitySetToBasicDtoList(entities);

        return resultBasic;

    }

    @Override
    public MovieDTO update(Long id, MovieDTO movieDTO) {
        return null;
    }

    public void removePersonage(Long id, Long idPersonage){
        EntityMovie movieEntity= this.movieRepository.getById(id);
        movieEntity.getPersonages().size();

        EntityPersonage entityPersonage= this.personageService.getEntityById(idPersonage);
        movieEntity.removePersonage(entityPersonage);
        this.movieRepository.save(movieEntity);
    }
    public void addPersonage(Long id, Long idPersonage){
        EntityMovie movieEntity= this.movieRepository.getById(id);
        movieEntity.getPersonages().size();

        EntityPersonage entityPersonage= this.personageService.getEntityById(idPersonage);
        movieEntity.addPersonage(entityPersonage);
        this.movieRepository.save(movieEntity);
    }

}
