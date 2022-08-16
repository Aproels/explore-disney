package com.alkemy.disney.control;



import com.alkemy.disney.dto.PersonageDTO;
import com.alkemy.disney.entitys.EntityPersonage;
import com.alkemy.disney.mapper.PersonageMapper;
import com.alkemy.disney.repository.PersonageRepository;
import com.alkemy.disney.service.PersonageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<List<PersonageDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long age,
            @RequestParam(required = false) Long weight,
            @RequestParam(required = false) Set<Long> movieSeries,
            @RequestParam(required = false, defaultValue = "ASC") String order

    ) {

        List<PersonageDTO> personage = this.personageService.getByFilters(name, age, weight, movieSeries, order);

        return ResponseEntity.ok(personage);


    }


    @GetMapping("/All")
    public ResponseEntity<List<PersonageDTO>> getAll() {

        List<PersonageDTO> allPersonages = personageService.getAllPersonages();


        return ResponseEntity.ok().body(allPersonages);
    }


    @PostMapping("/Create")
    public ResponseEntity<PersonageDTO> save(@RequestBody PersonageDTO personageDTO) {

        PersonageDTO personageSaved = personageService.save(personageDTO);


        return ResponseEntity.status(HttpStatus.CREATED).body(personageSaved);
    }


    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.personageService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/Buscar/{id}")
    public ResponseEntity<Optional<EntityPersonage>> getPersonage(@PathVariable("id") Long id) {

        Optional<EntityPersonage> personages = personageService.findById(id);


        return ResponseEntity.ok().body(personages);
    }

    @PutMapping("/Update/{id}")
    public ResponseEntity<PersonageDTO> update(@PathVariable("id") Long id, @RequestBody PersonageDTO personageDTO) {

        PersonageDTO personageSaved = personageService.update(id,personageDTO);

        return ResponseEntity.ok().body(personageSaved);


    }

}



