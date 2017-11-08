package com.example.fluxflixservice;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {
    private final MovieRepository movieRepository;

    public DataLoader(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    private void loadData() {
        this.movieRepository.deleteAll().thenMany(
                Flux.just("Enter the Mono<Void>",
                        "Y Tu Mono También",
                        "Meet the Fluxers")
                        .map(Movie::new)
                        .flatMap(this.movieRepository::save))
                .subscribe(System.out::println);

//        System.out.println("\nAll records in DB:\n");
//        this.movieRepository
//                .findAll()
//                .subscribe(movie -> System.out.println("> Found in DB: " + movie.toString()));
    }
}
