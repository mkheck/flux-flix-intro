package com.example.fluxflixservicekotlin

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

//@RestController
@RequestMapping("/movies")
class MovieController(val movieService: MovieService) {

    @GetMapping
    fun getMovies(): Flux<Movie> = movieService.getAllMovies()

    @GetMapping("/{id}")
    fun getMovie(@PathVariable id: String): Mono<Movie> =
            movieService.getMovieById(id)

    @GetMapping(value = "/{id}/events", produces = arrayOf(MediaType.TEXT_EVENT_STREAM_VALUE))
    fun getEvents(@PathVariable id: String): Flux<MovieEvent> =
            movieService.getEventsForMovie(id)
}