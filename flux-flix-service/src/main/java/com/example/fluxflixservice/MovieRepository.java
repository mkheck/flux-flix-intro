package com.example.fluxflixservice;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

interface MovieRepository extends ReactiveMongoRepository<Movie, String> {
}
