package com.alkemy.disney.service.imp;



import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.entitys.EntityMovie;
import com.alkemy.disney.entitys.EntityPersonage;
import com.alkemy.disney.exeption.ParameterNotFound;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.repository.PersonageRepository;
import com.alkemy.disney.repository.specifications.MovieSpecification;
import com.alkemy.disney.service.GenderService;
import com.alkemy.disney.service.MovieService;
import com.alkemy.disney.service.PersonageService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//import static com.alkemy.disney.mapper.MovieMapper.formatter;

@Service

public class MovieServiceimp implements MovieService {

    @Autowired
    private GenderService genderService;

    @Autowired

    private PersonageRepository personageRepository;


    @Autowired
    private PersonageService personageService;

    @Autowired
    private MovieSpecification movieSpecification;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired

    private MovieRepository movieRepository;

    public MovieDTO getDetailsById(Long id){
      EntityMovie entity=getMovieEntityById(id);


        return movieMapper.MovieEntityToDto(entity,true);
    }

    public List<MovieBasicDTO> getByFilters(String title, Long genderId,String creationDate){
        MovieFiltersDTO filtersDTO= new MovieFiltersDTO(title, genderId,creationDate);
        List<EntityMovie> entities = movieRepository.findAll(this.movieSpecification.getByFilters(filtersDTO));
        List<MovieBasicDTO> basicDto= movieMapper.MovieEntitySetToBasicDtoList(entities);

        return basicDto;



    }

    public MovieDTO save(MovieDTO movieDTO){

        EntityMovie entity = movieMapper.MovieDtoToEntity(movieDTO);

        EntityMovie entitySaved= movieRepository.save(entity);

        MovieDTO result= movieMapper.MovieEntityToDto(entitySaved,true);

        return result;
    }




    public void delete(Long id){
        EntityMovie movie = getMovieEntityById(id);


        movieRepository.delete(movie);
    }

    public MovieDTO update(Long id, MovieDTO movieDTO) {
        EntityMovie movie = getMovieEntityById(id);

        //sin los "if" cada vez que se actualizaba un solo parametro,se volvian nulos los demas
        if(!StringUtils.hasLength(movieDTO.getTitle())){
            movie.setTitle(movie.getTitle());
            }else{
            movie.setTitle(movieDTO.getTitle());
        }

        if(!StringUtils.hasLength(movieDTO.getImage())){
            movie.setImage(movie.getImage());
            }else{
            movie.setImage(movieDTO.getImage());
        }

        if(movieDTO.getScore() ==null){
            movie.setScore(movie.getScore());
        }else {
            movie.setScore(movieDTO.getScore());

        }


        if(!StringUtils.hasLength(movieDTO.getCreationDate())){
            movie.setCreationDate(movie.getCreationDate());
        }else{
            movie.setCreationDate(this.movieMapper.StringToLocalDate(movieDTO.getCreationDate()));
        }

        movieRepository.save(movie);
        return movieMapper.MovieEntityToDto(movie, true);
    }

    public MovieDTO removePersonage(Long movieId, Long personageId){
        EntityMovie movieEntity= getMovieEntityById(movieId);
        EntityPersonage entityPersonage=getPersonageEntityById(personageId);

        movieEntity.removePersonage(entityPersonage);


        movieRepository.save(movieEntity);
        return movieMapper.MovieEntityToDto(movieEntity,true);
    }
    public MovieDTO addPersonage(Long movieId, Long personageId){
        EntityMovie movieEntity= getMovieEntityById(movieId);

        EntityPersonage entityPersonage=getPersonageEntityById(personageId);
        movieEntity.addPersonage(entityPersonage);
        movieRepository.save(movieEntity);

        return movieMapper.MovieEntityToDto(movieEntity,true);
    }
    private EntityMovie getMovieEntityById(Long id) {
        Optional<EntityMovie> movie = movieRepository.findById(id);
        if(movie.isEmpty()){
            throw new ParameterNotFound("ID No valido");
        }
        return movie.get();
    }
    private EntityPersonage getPersonageEntityById(Long id) {
        Optional<EntityPersonage> personage = personageRepository.findById(id);
        if(personage.isEmpty()){
            throw new ParameterNotFound("ID No valido");
        }
        return personage.get();
    }


}
