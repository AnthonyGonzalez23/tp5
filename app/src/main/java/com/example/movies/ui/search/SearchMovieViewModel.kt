package com.example.movies.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.repository.OmdbMovieRepository
import com.example.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchMovieViewModel : ViewModel() {

    private val movieRepository: MovieRepository = OmdbMovieRepository()

    private val _uiState = MutableStateFlow<SearchMovieState>(SearchMovieState.Empty)
    val uiState: StateFlow<SearchMovieState> get() = _uiState.asStateFlow()

    fun searchMovies(text: String) {
        if (text.isBlank()) {
            _uiState.value = SearchMovieState.Empty
            return
        }

        viewModelScope.launch {
            _uiState.value = SearchMovieState.Loading
            try {
                val movies = movieRepository.searchMovie(text)
                if (movies.isEmpty()) {
                    _uiState.value = SearchMovieState.Empty
                } else {
                    _uiState.value = SearchMovieState.Loaded(movies)
                }
            } catch (e: Exception) {
                val errorMessage = e.message ?: "Unknown error occurred"
                _uiState.value = SearchMovieState.Error(errorMessage)
            }
        }
    }
}