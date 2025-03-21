package com.example.movies.domain.repository

import com.example.movies.domain.model.MovieDetail
import com.example.movies.domain.model.MovieShort

interface MovieRepository {
    suspend fun searchMovie(search: String): List<MovieShort>
    suspend fun getMovieDetail(movieId: String): Result<MovieDetail>
}