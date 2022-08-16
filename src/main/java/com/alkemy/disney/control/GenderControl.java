package com.alkemy.disney.control;


import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("Genero")
public class GenderControl {
    @Autowired
    private  GenderService genderservice;

    @PostMapping
    public ResponseEntity<GenderDTO> save(@RequestBody GenderDTO gender){

        GenderDTO genderSaved = genderservice.save(gender);


        return ResponseEntity.status(HttpStatus.CREATED).body(genderSaved);
    }
}
