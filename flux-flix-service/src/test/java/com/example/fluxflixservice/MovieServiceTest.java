package com.example.fluxflixservice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {
    @Autowired
    private MovieService movieService;

    @Test
    public void getEventsForMovieTake10() throws Exception {
        String movieId = this.movieService
                .getAllMovies()
                .blockFirst()
                .getId();

        StepVerifier.withVirtualTime(() -> this.movieService.getEventsForMovie(movieId)
                .take(10)
                .collectList())
                .thenAwait(Duration.ofHours(10))
                .consumeNextWith(list -> Assert.assertTrue(list.size() == 10))
                .verifyComplete();
    }

}