package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.entitys.EntityGender;
import org.springframework.stereotype.Component;


@Component
public class GenderMapper {
    public EntityGender genderDAOtoEntity(GenderDTO genderDTO){
        EntityGender entityGender= new EntityGender();

        entityGender.setNombre(genderDTO.getNombre());
        entityGender.setImagen(genderDTO.getImagen());

        return entityGender;
    }

    public GenderDTO genderEntitytoDao(EntityGender entity){
        GenderDTO genderDTO = new GenderDTO();
        genderDTO.setId(entity.getId());
        genderDTO.setNombre(entity.getNombre());
        genderDTO.setImagen(entity.getImagen());


        return genderDTO;


    }

}
