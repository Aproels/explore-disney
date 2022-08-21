package com.alkemy.disney.service;



import com.alkemy.disney.dto.PersonageBasicDTO;
import com.alkemy.disney.dto.PersonageDTO;

import java.util.List;

import java.util.Set;

public interface PersonageService {

    List<PersonageBasicDTO> getDetailsByFilters(String name, Long age, Double weight, Set<Long>movies);

    PersonageDTO save(PersonageDTO personageDTO);


    void delete(Long id);

   PersonageDTO getById(Long id);

  PersonageDTO update(Long id,PersonageDTO personageDTO);


}
