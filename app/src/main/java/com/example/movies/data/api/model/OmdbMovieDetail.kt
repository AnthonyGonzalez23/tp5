package com.example.movies.data.api.model

import com.google.gson.annotations.SerializedName

data class OmdbMovieDetail(
    @SerializedName("imdbID")
    val imdbId: String = "",
    @SerializedName("Title")
    val title: String = "",
    @SerializedName("Poster")
    val poster: String = "",
    @SerializedName("Year")
    val year: String = "",
    @SerializedName("Rated")
    val rated: String = "",
    @SerializedName("Runtime")
    val runtime: String = "",
    @SerializedName("Genre")
    val genre: String = "",
    @SerializedName("Director")
    val director: String = "",
    @SerializedName("Writer")
    val writer: String = "",
    @SerializedName("Actors")
    val actors: String = "",
    @SerializedName("Plot")
    val plot: String = "",
    @SerializedName("Awards")
    val awards: String = "",
    @SerializedName("imdbRating")
    val imdbRating: String = "",
    @SerializedName("Response")
    val response: String? = null,
    @SerializedName("Error")
    val error: String? = null
)