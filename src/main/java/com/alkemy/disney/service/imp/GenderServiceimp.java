package com.alkemy.disney.service.imp;


import com.alkemy.disney.dao.GenderDTO;
import com.alkemy.disney.entitys.EntityGender;
import com.alkemy.disney.mapper.GenderMapper;
import com.alkemy.disney.repository.GenderRepository;
import com.alkemy.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceimp implements GenderService {
    @Autowired
    private GenderMapper genderMapper;

    @Autowired

    private GenderRepository genderRepository;
    public GenderDTO save(GenderDTO genderDTO){

        EntityGender entity = genderMapper.genderDAOtoEntity(genderDTO);

        EntityGender entitySaved= genderRepository.save(entity);
        GenderDTO result= genderMapper.genderEntitytoDao(entitySaved);





        return result;
    }
}
