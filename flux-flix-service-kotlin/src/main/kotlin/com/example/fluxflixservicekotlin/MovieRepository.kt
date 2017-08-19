package com.example.fluxflixservicekotlin

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface MovieRepository: ReactiveMongoRepository<Movie, String> {
}