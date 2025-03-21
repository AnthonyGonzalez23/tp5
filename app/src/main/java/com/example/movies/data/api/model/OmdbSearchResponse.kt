package com.example.movies.data.api.model

import com.google.gson.annotations.SerializedName

data class OmdbSearchResponse(
    @SerializedName("Search")
    val movies: List<OmdbMovieShort>? = null,
    @SerializedName("totalResults")
    val totalResults: String? = null,
    @SerializedName("Response")
    val response: String? = null,
    @SerializedName("Error")
    val error: String? = null
)