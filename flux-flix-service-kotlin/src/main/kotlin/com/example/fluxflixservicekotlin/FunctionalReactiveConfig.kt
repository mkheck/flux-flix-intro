package com.example.fluxflixservicekotlin

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

@Configuration
class FunctionalReactiveConfig(val movieHandler: MovieHandler) {
    @Bean
    fun router() = router {
        "/".nest {
            accept(MediaType.APPLICATION_JSON).nest {
                GET("/movies", { request -> movieHandler.getMovies(request) })
            }
            accept(MediaType.APPLICATION_JSON).nest {
                GET("/movies/{id}", { request -> movieHandler.getMovie(request) })
            }
            accept(MediaType.TEXT_EVENT_STREAM).nest {
                GET("/movies/{id}/events", { request -> movieHandler.getEvents(request) })
            }
        }
    }


//    @Bean
//    fun routerFunction(movieHandler: MovieHandler): RouterFunction<ServerResponse> {
//        return router {
//            ("/movies").nest {
//                GET("") {
//                    serverRequest -> ServerResponse
//                        .ok()
//                        .body(movieHandler.getMovies(serverRequest))
//                }
//                GET("/{id}") {
//                    serverRequest ->  ServerResponse
//                        .ok()
//                        .body(movieHandler.getMovie(serverRequest))
//                }
//                GET("/{id}/events") {
//                    serverRequest -> ServerResponse
//                        .ok()
//                        .contentType(MediaType.TEXT_EVENT_STREAM)
//                        .body(movieHandler.getEvents(serverRequest))
//                }
//            }
//        }
//    }
}