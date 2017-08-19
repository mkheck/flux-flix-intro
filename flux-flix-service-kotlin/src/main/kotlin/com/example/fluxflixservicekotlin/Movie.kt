package com.example.fluxflixservicekotlin

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Movie(val id: String? = null, val title: String? = null)