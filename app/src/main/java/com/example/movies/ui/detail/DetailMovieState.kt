package com.example.movies.ui.detail

import com.example.movies.domain.model.MovieDetail

sealed class DetailMovieState {
    data object Loading : DetailMovieState()
    data class Loaded(val movie: MovieDetail) : DetailMovieState()
    data class Error(val message: String) : DetailMovieState()
}