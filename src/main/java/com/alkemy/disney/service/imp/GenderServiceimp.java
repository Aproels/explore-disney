package com.alkemy.disney.service.imp;


import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.entitys.EntityGender;
import com.alkemy.disney.exeption.ParameterNotFound;
import com.alkemy.disney.mapper.GenderMapper;
import com.alkemy.disney.repository.GenderRepository;
import com.alkemy.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenderServiceimp implements GenderService {
    @Autowired
    private GenderMapper genderMapper;

    @Autowired

    private GenderRepository genderRepository;
    public GenderDTO save(GenderDTO genderDTO){

        EntityGender entity = genderMapper.genderDtotoEntity(genderDTO);

        EntityGender entitySaved= genderRepository.save(entity);
        GenderDTO result= genderMapper.genderEntitytoDto(entitySaved);

        return result;
    }

        public EntityGender getGenderEntityById(Long id) {
        Optional<EntityGender> gender = genderRepository.findById(id);
        if (gender.isEmpty()) {
            throw new ParameterNotFound("Genre with id: " + id + " not found");
        }
        return gender.get();
    }
}
