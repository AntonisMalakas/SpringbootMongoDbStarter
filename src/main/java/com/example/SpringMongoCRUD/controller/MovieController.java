package com.example.SpringMongoCRUD.controller;


import com.example.SpringMongoCRUD.model.Movie;
import com.example.SpringMongoCRUD.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api")
@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepo;

    @RequestMapping(method = RequestMethod.POST, value = "/movie")
    public ResponseEntity<String> createMovie(@RequestBody Movie movie) {
        try {
            movieRepo.save(movie);
            return new ResponseEntity("Successfully added movie " + movie.getTitle(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies")
    public ResponseEntity getAllMovies() {
        try {
            List<Movie> movieList = movieRepo.findAll();
            return new ResponseEntity(movieList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/mpvie/{id}")
    public ResponseEntity deleteMoveById(@PathVariable("id") String id) {
        try {
            movieRepo.deleteById(id);
            return new ResponseEntity("Successfully deleted movie", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/movie/{id}")
    public ResponseEntity updateMovieById(@PathVariable("id") String id, @RequestBody Movie newMovie) {
        try {
            Optional<Movie> savedMovie = movieRepo.findById(id);
            if (savedMovie.isPresent()) {
                Movie movieToSave = savedMovie.get();
                movieToSave.setTitle(newMovie.getTitle());
                movieToSave.setRating(newMovie.getRating());
                movieToSave.setGenre(newMovie.getGenre());
                movieRepo.save(movieToSave);
                return new ResponseEntity("Updated Movie with id " + id, HttpStatus.OK);

            } else {
                return new ResponseEntity("Movie Not Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
