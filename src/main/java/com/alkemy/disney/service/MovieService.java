package com.alkemy.disney.service;

import com.alkemy.disney.dao.MovieBasicDTO;
import com.alkemy.disney.dao.MovieDTO;

import java.util.List;
import java.util.Set;

public interface MovieService {

   List<MovieDTO> getByFilters( String title, String creationDate, String gender,String order);

   MovieDTO save(MovieDTO movieDTO);

    MovieDTO getDetailsById(Long id);


   void delete(Long id);

   List<MovieBasicDTO> getAllMovie();

   MovieDTO update(Long id, MovieDTO movieDTO);

   void addPersonage(Long id,Long idPersonage);
   void removePersonage(Long id,Long idPersonage);
}
