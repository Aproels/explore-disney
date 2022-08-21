package com.alkemy.disney.control;



import com.alkemy.disney.dto.PersonageBasicDTO;
import com.alkemy.disney.dto.PersonageDTO;

import com.alkemy.disney.mapper.PersonageMapper;
import com.alkemy.disney.repository.PersonageRepository;
import com.alkemy.disney.service.PersonageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import java.util.Set;

@RestController
@RequestMapping("Personages")
public class PersonageControl {
    @Autowired
    private PersonageService personageService;

    @Autowired
    private PersonageMapper personageMapper;
    @Autowired
    private PersonageRepository personageRepository;

    @GetMapping
    public ResponseEntity<List<PersonageBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long age,
            @RequestParam(required = false) Double weight,
            @RequestParam(required = false) Set<Long> moviesId


    ) {

        List<PersonageBasicDTO> personage =personageService.getDetailsByFilters(name, age, weight, moviesId);

        return ResponseEntity.ok(personage);


    }



    @PostMapping()
    public ResponseEntity<PersonageDTO> save(@Valid @RequestBody PersonageDTO personageDTO) {

        PersonageDTO personageSaved = personageService.save(personageDTO);


        return ResponseEntity.status(HttpStatus.CREATED).body(personageSaved);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonageDTO> getById(@Valid@PathVariable("id") Long id) {

        PersonageDTO personage = personageService.getById(id);


        return ResponseEntity.ok().body(personage);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid@PathVariable Long id) {
        personageService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




    @PutMapping("/{id}")
    public ResponseEntity<PersonageDTO> update(@Valid@PathVariable("id") Long id, @RequestBody PersonageDTO personageDTO) {

        PersonageDTO personageSaved = personageService.update(id,personageDTO);

        return ResponseEntity.ok().body(personageSaved);


    }

}



