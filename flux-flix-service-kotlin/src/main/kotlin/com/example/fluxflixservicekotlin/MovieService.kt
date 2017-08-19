package com.example.fluxflixservicekotlin

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.SynchronousSink
import java.time.Duration
import java.util.*

@Service
class MovieService(val movieRepository: MovieRepository) {

    fun getAllMovies(): Flux<Movie> = movieRepository.findAll()

    fun getMovieById(id: String): Mono<Movie> = movieRepository.findById(id)

    fun getEventsForMovie(id: String): Flux<MovieEvent> =
        Flux.generate<MovieEvent> { sink: SynchronousSink<MovieEvent> -> sink.next(MovieEvent(id, Date())) }
                .delayElements(Duration.ofSeconds(1))
}