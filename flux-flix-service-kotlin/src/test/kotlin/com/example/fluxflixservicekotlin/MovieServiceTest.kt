package com.example.fluxflixservicekotlin

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import reactor.test.StepVerifier
import java.time.Duration

@RunWith(SpringRunner::class)
@SpringBootTest
class MovieServiceTest {
    @Autowired lateinit var movieService: MovieService

    @Test
    fun getEventsForMovieTake10() {
        val movieId: String = movieService.getAllMovies().blockFirst()!!.id!!

        StepVerifier.withVirtualTime { movieService.getEventsForMovie(movieId)
                .take(10)
                .collectList()}
                .thenAwait(Duration.ofHours(10))
                .consumeNextWith { list -> Assert.assertTrue(list.size == 10) }
                .verifyComplete()
    }

}