package com.example.movies.data.api

import com.example.movies.data.api.model.OmdbMovieDetail
import com.example.movies.data.api.model.OmdbSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET("/")
    suspend fun searchMovie(
        @Query("s") title: String,
        @Query("apiKey") apiKey: String
    ): OmdbSearchResponse?

    @GET("/")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apiKey") apiKey: String,
        @Query("plot") plot: String = "full"
    ): OmdbMovieDetail
}