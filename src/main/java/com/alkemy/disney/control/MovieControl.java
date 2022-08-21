package com.alkemy.disney.control;


import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("Movies")
public class MovieControl {
    @Autowired
    private MovieService movieService;

/*
    @GetMapping
    public ResponseEntity <List<MovieBasicDTO>> getAll(){

        List<MovieBasicDTO> basicDto=movieService.getAll();

        return ResponseEntity.ok(basicDto);

    }
*/

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getDetailsById(@Valid @PathVariable Long id){
         MovieDTO movie=this.movieService.getDetailsById(id);
        return ResponseEntity.ok(movie);
    }



    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getDetailsByFilters(

            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long genderId,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<MovieBasicDTO> movies = movieService.getByFilters(title, genderId, order);
        return ResponseEntity.ok(movies);

    }



    @PostMapping()
    public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO movieDTO){

        MovieDTO movieSaved = movieService.save(movieDTO);


        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update( @PathVariable("id") Long id, @RequestBody MovieDTO movieDTO) {

        MovieDTO movieUpdated = movieService.update(id,movieDTO);

        return ResponseEntity.ok().body(movieUpdated);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        movieService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{MovieId}/personages/{PersonagesId}")
    public ResponseEntity<MovieDTO> addPersonage(@Valid @PathVariable Long movieId, @PathVariable Long personageId){

        this.movieService.addPersonage(movieId,personageId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/Personages/{PersonagesId}")
    public ResponseEntity<MovieDTO> removePersonage(@PathVariable Long movieId, @PathVariable Long personageId){

        this.movieService.removePersonage(movieId,personageId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
