package com.example.movies.ui.search

import com.example.movies.domain.model.MovieShort

sealed class SearchMovieState {
    data object Loading : SearchMovieState()
    data object Empty : SearchMovieState()
    data class Loaded(val movies: List<MovieShort>) : SearchMovieState()
    data class Error(val message: String) : SearchMovieState()
}