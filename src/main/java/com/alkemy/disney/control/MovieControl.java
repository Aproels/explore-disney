package com.alkemy.disney.control;


import com.alkemy.disney.dao.MovieBasicDTO;
import com.alkemy.disney.dao.MovieDTO;
import com.alkemy.disney.dao.PersonageDTO;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping ("Peliculas")
public class MovieControl {
    @Autowired
    private MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getDetailsById(@PathVariable Long id){
         MovieDTO movie=this.movieService.getDetailsById(id);
        return ResponseEntity.ok(movie);
    }



    @GetMapping
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(

            @RequestParam(required = false) String title,
            @RequestParam(required = false) String creationDate,
            @RequestParam(required = false)  String genero,
            @RequestParam(required = false, defaultValue = "ASC") String order

    ) {

      List<MovieDTO> movies = this.movieService.getByFilters(title, creationDate, genero, order);

     return ResponseEntity.ok(movies);


    }

    @GetMapping("/All")
    public ResponseEntity<List<MovieBasicDTO>> getAll(){

        List<MovieBasicDTO> movies = movieService.getAllMovie();


        return ResponseEntity.ok().body(movies);
    }



    @PostMapping("/Create")
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO){

        MovieDTO movieSaved = movieService.save(movieDTO);


        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }
    @PutMapping("/Update/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable("id") Long id, @RequestBody MovieDTO movieDTO) {

        MovieDTO movieUpdated = movieService.update(id,movieDTO);

        return ResponseEntity.ok().body(movieUpdated);


    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.movieService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/personages/{idpersonages}")
    public ResponseEntity<Void> addPersonage(@PathVariable Long id, @PathVariable Long idPersonage){

        this.movieService.addPersonage(id,idPersonage);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/personages/{idpersonages}")
    public ResponseEntity<Void> removePersonage(@PathVariable Long id, @PathVariable Long idPersonage){

        this.movieService.removePersonage(id,idPersonage);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
