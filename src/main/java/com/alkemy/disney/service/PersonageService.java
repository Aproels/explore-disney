package com.alkemy.disney.service;



import com.alkemy.disney.dao.PersonageDTO;
import com.alkemy.disney.entitys.EntityPersonage;
import com.alkemy.disney.repository.PersonageRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonageService {

    List<PersonageDTO> getByFilters(String name, Long age, Long weight, Set<Long>movieSeries, String order);

    PersonageDTO save(PersonageDTO personageDTO);

    List<PersonageDTO> getAllPersonages();
    void delete(Long id);

    Optional<EntityPersonage> findById(Long id);

  PersonageDTO update(Long id,PersonageDTO personageDTO);

    EntityPersonage getEntityById(Long idPersonage);
}
