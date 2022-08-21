package com.alkemy.disney.service;


import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.entitys.EntityGender;

public interface GenderService {

    GenderDTO save(GenderDTO genderdao);

    EntityGender getGenderEntityById(Long Id);
}
