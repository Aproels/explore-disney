package com.alkemy.disney.service.imp;



import com.alkemy.disney.dao.PersonageDTO;
import com.alkemy.disney.dao.PersonageFiltersDTO;
import com.alkemy.disney.entitys.EntityPersonage;

import com.alkemy.disney.mapper.PersonageMapper;
import com.alkemy.disney.repository.PersonageRepository;
import com.alkemy.disney.repository.specifications.PersonageSpecification;
import com.alkemy.disney.service.PersonageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonageServiceimp implements PersonageService {


    @Autowired
    private PersonageMapper personageMapper;

    @Autowired
    private PersonageRepository personageRepository;
    @Autowired
    private PersonageSpecification personageSpecification;

    public List<PersonageDTO> getByFilters(String name, Long age, Long weight, Set<Long> movieSeries, String order){
        PersonageFiltersDTO filtersDTO= new  PersonageFiltersDTO(name, age, weight, movieSeries, order);
        List<EntityPersonage> entities = this.personageRepository.findAll(this.personageSpecification.getByFilters(filtersDTO));
        List<PersonageDTO> dtos= this.personageMapper.PersonageEntitySetToDtoList(entities, false);

    return dtos;

    }


    public List<PersonageDTO> getAllPersonages() {

        List<EntityPersonage> entities = personageRepository.findAll();
        List<PersonageDTO> result= personageMapper.PersonageEntityListToDtoList(entities, true);

        return result;
    }

    public PersonageDTO save(PersonageDTO personageDTO){

        EntityPersonage entity = personageMapper.PersonageDtoToEntity(personageDTO);

        EntityPersonage  entitySaved=personageRepository.save(entity);

        PersonageDTO result= personageMapper.PersonageEntityToDto(entitySaved,true);

        return result;
    }

    public void delete(Long id){

        personageRepository.deleteById(id);
    }


    public Optional<EntityPersonage> findById(Long id) {


        return personageRepository.findById(id);
    }


 public PersonageDTO update(Long id,PersonageDTO personageDTO) {

     Optional<EntityPersonage> entity = this.personageRepository.findById(id);
     if (entity.isPresent()) {
         PersonageDTO dto = this.personageMapper.PersonageEntityToDto(entity.get(), true);
         dto.setImagen(personageDTO.getImagen());
         dto.setHistoria(personageDTO.getHistoria());
         dto.setNombre(personageDTO.getNombre());
         dto.setPeso(personageDTO.getPeso());
         dto.setEdad(personageDTO.getEdad());
         personageMapper.PersonageDtoToEntity(dto);

         return dto;

     }
     return null;
 }


    @Override
    public EntityPersonage getEntityById(Long idPersonage) {
        return null;
    }

}
