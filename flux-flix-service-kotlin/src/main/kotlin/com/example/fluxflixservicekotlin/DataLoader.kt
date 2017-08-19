package com.example.fluxflixservicekotlin

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import javax.annotation.PostConstruct

@Component
class DataLoader(val movieRepository: MovieRepository) {
    @PostConstruct
    fun loadData() {
        movieRepository.deleteAll().thenMany(
                Flux.just("Enter the Mono<Void>",
                        "Y Tu Mono También",
                        "Meet the Fluxers")
                        .map { title -> Movie(title) }
                        .flatMap { movie -> movieRepository.save(movie) })

        println("\nAll records in DB:\n");
        movieRepository
                .findAll()
                .subscribe(System.out::println)
    }
}