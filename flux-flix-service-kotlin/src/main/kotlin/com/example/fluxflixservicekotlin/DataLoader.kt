package com.example.fluxflixservicekotlin

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import javax.annotation.PostConstruct

@Component
class DataLoader(private val movieRepository: MovieRepository) {
    @PostConstruct
    fun loadData() {
        movieRepository.deleteAll().thenMany(
                Flux.just("Enter the Mono<Void>",
                        "Y Tu Mono También",
                        "Meet the Fluxers")
                        .map { Movie(title = it) }
                        .flatMap { movieRepository.save(it) })
                .subscribe { println(it) }
    }
}