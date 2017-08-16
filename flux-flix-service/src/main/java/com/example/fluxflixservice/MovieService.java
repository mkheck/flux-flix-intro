package com.example.fluxflixservice;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    Flux<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }

    Mono<Movie> getMovieById(String id) {
        return this.movieRepository.findById(id);
    }

    // Until service goes public & user base grows, we generate
    // simulated viewing events
    Flux<MovieEvent> getEventsForMovie(String id) {
        return Flux.<MovieEvent>generate(sink -> sink.next(
                new MovieEvent(id, new Date())))
                .delayElements(Duration.ofSeconds(1));
    }
}
