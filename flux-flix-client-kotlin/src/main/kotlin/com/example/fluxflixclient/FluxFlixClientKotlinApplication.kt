package com.example.fluxflixclient

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import java.util.*

@SpringBootApplication
class FluxFlixClientKotlinApplication {
    @Bean
    fun client() = WebClient.create("http://localhost:8080/movies")

    @Bean
    fun demo(client: WebClient) = CommandLineRunner {
        client.get()
                .retrieve()
                .bodyToFlux<Movie>()
                .filter { it.title.equals(other = "enter the mono<void>", ignoreCase = true) }
                .flatMap { client.get()
                        .uri("/{id}/events", it.id)
                        .retrieve()
                        .bodyToFlux<MovieEvent>()}
                .subscribe { println(it) }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(FluxFlixClientKotlinApplication::class.java, *args)
}

data class MovieEvent(val movieId: String, val viewDate: Date)

data class Movie(val id: String, val title: String)