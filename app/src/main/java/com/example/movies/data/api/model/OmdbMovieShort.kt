package com.example.movies.data.api.model

import com.google.gson.annotations.SerializedName

data class OmdbMovieShort(
    @SerializedName("imdbID")
    val imdbId: String = "",
    @SerializedName("Title")
    val title: String = "",
    @SerializedName("Poster")
    val poster: String = "",
    @SerializedName("Year")
    val year: String = "",
    @SerializedName("Type")
    val type: String = ""
)