package com.example.movies.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.repository.OmdbMovieRepository
import com.example.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailMovieViewModel : ViewModel() {

    private val movieRepository: MovieRepository = OmdbMovieRepository()

    private val _uiState = MutableStateFlow<DetailMovieState>(DetailMovieState.Loading)
    val uiState: StateFlow<DetailMovieState> get() = _uiState.asStateFlow()

    fun loadMovieDetail(movieId: String) {
        viewModelScope.launch {
            _uiState.value = DetailMovieState.Loading
            movieRepository.getMovieDetail(movieId).fold(
                onSuccess = { movieDetail ->
                    _uiState.value = DetailMovieState.Loaded(movieDetail)
                },
                onFailure = { error ->
                    _uiState.value = DetailMovieState.Error(error.message ?: "Erreur inconnue")
                }
            )
        }
    }
}