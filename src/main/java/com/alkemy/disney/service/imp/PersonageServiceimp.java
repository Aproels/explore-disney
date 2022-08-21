package com.alkemy.disney.service.imp;



import com.alkemy.disney.dto.PersonageBasicDTO;
import com.alkemy.disney.dto.PersonageDTO;
import com.alkemy.disney.dto.PersonageFiltersDTO;
import com.alkemy.disney.entitys.EntityPersonage;

import com.alkemy.disney.exeption.ParameterNotFound;
import com.alkemy.disney.mapper.PersonageMapper;
import com.alkemy.disney.repository.PersonageRepository;
import com.alkemy.disney.repository.specifications.PersonageSpecification;
import com.alkemy.disney.service.PersonageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.lang.String.valueOf;

@Service
public class PersonageServiceimp implements PersonageService {


    @Autowired
    private PersonageMapper personageMapper;

    @Autowired
    private PersonageRepository personageRepository;
    @Autowired
    private PersonageSpecification personageSpecification;



    public List<PersonageBasicDTO> getDetailsByFilters(String name, Long age, Double weight, Set<Long> movies) {
        PersonageFiltersDTO filtersDTO= new  PersonageFiltersDTO(name, age, weight, movies);
        List<EntityPersonage> entities = this.personageRepository.findAll(this.personageSpecification.getByFilters(filtersDTO));
        List<PersonageBasicDTO> basicDto=personageMapper.PersonageEntitySetToBasicDtoList(entities);


        return basicDto;

    }

    public PersonageDTO save(PersonageDTO personageDTO){

        EntityPersonage entity = personageMapper.PersonageDtoToEntity(personageDTO);

        EntityPersonage  entitySaved=personageRepository.save(entity);

        PersonageDTO result= personageMapper.PersonageEntityToDto(entitySaved,true);



        return  result;
    }

    public void delete(Long id){
        EntityPersonage personage = getPersonageEntityById(id);


        personageRepository.delete(personage);
    }


    public PersonageDTO getById(Long id) {
        EntityPersonage entity =  getPersonageEntityById(id);

        return personageMapper.PersonageEntityToDto(entity, true);

    }

    public PersonageDTO update(Long id,PersonageDTO personageDTO) {
        EntityPersonage personage = getPersonageEntityById(id);

        //sin los "if" cada vez que se actualizaba un solo parametro,se volvian nulos los demas
        if(!StringUtils.hasLength(personageDTO.getName())){
        personage.setName(personage.getName());
            }else{
                personage.setName(personageDTO.getName());
                }

        if(!StringUtils.hasLength(personageDTO.getImage())){
            personage.setImage(personage.getImage());
            }else{
            personage.setImage(personageDTO.getImage());
            }

        if(!StringUtils.hasLength(personageDTO.getHistory())){
            personage.setHistory(personage.getHistory());
            }else{
            personage.setHistory(personageDTO.getHistory());
        }

        if(personageDTO.getAge() ==null){
            personage.setAge(personage.getAge());
             }else {
             personage.setAge(personageDTO.getAge());

         }

        if(personageDTO.getWeight()==null){
             personage.setWeight(personage.getWeight());
            }else{
             personage.setWeight(personageDTO.getWeight());

         }

        personageRepository.save(personage);


        return personageMapper.PersonageEntityToDto(personage, true);
     }

    private EntityPersonage getPersonageEntityById(Long id) {
        Optional<EntityPersonage> personage = personageRepository.findById(id);
        if (personage.isEmpty()) {
            throw new ParameterNotFound("ID No valido");
        }
        return personage.get();
    }

}
