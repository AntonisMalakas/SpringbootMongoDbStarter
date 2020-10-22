package com.example.SpringMongoCRUD.repository;

import com.example.SpringMongoCRUD.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {
}
