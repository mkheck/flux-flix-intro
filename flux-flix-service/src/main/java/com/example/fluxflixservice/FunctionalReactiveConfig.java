package com.example.fluxflixservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;

/*
    Spring WebFlux enables functional definition of reactive routing
    function/routes
 */
//@Configuration
public class FunctionalReactiveConfig {
    @Bean
    RouterFunction<?> routerFunction(MovieService movieService) {
        return route(
                        GET("/movies"), request -> ServerResponse
                                .ok()
                                .body(movieService.getAllMovies(), Movie.class))
                .andRoute(
                        GET("/movies/{id}"), request -> ServerResponse
                                .ok()
                                .body(movieService.getMovieById(request.pathVariable("id")), Movie.class))
                .andRoute(
                        GET("/movies/{id}/events"), request -> ServerResponse
                                .ok()
                                .contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(movieService.getEventsForMovie(request.pathVariable("id")), MovieEvent.class));
    }
}
