package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.entitys.EntityGender;
import org.springframework.stereotype.Component;


@Component
public class GenderMapper {
    public EntityGender genderDtotoEntity(GenderDTO genderDTO){
        EntityGender entityGender= new EntityGender();

        entityGender.setNombre(genderDTO.getNombre());
        entityGender.setImagen(genderDTO.getImagen());

        return entityGender;
    }

    public GenderDTO genderEntitytoDto(EntityGender entity){
        GenderDTO genderDto = new GenderDTO();
        genderDto.setId(entity.getId());
        genderDto.setNombre(entity.getNombre());
        genderDto.setImagen(entity.getImagen());


        return genderDto;


    }

}
