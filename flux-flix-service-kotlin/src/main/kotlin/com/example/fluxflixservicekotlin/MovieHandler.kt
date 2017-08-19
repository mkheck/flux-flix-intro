package com.example.fluxflixservicekotlin

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body

@Component
class MovieHandler(val movieService: MovieService) {

    fun getMovies(request: ServerRequest) = ServerResponse
            .ok()
            .body(movieService.getAllMovies())

    fun getMovie(request: ServerRequest) = ServerResponse
            .ok()
            .body(movieService.getMovieById(request.pathVariable("id")))

    fun getEvents(request: ServerRequest) = ServerResponse
            .ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .body(movieService.getEventsForMovie(request.pathVariable("id")))
}