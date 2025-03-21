package com.example.movies.domain.model

data class MovieDetail(
    val id: String,
    val title: String,
    val posterUrl: String,
    val year: String,
    val rated: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val awards: String,
    val rating: String
)