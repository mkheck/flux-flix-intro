package com.example.fluxflixservice;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MovieHandler {
    private final MovieService movieService;

    public MovieHandler(MovieService movieService) {
        this.movieService = movieService;
    }

    Mono<ServerResponse> getMovies(ServerRequest request) {
        return ServerResponse.ok()
                .body(this.movieService.getAllMovies(), Movie.class);
    }

    Mono<ServerResponse> getMovie(ServerRequest request) {
        return ServerResponse.ok()
                .body(this
                        .movieService
                        .getMovieById(request.pathVariable("id")), Movie.class);
    }

    Mono<ServerResponse> getEvents(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(this
                        .movieService
                        .getEventsForMovie(request.pathVariable("id")), MovieEvent.class);
    }
}
