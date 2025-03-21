package com.example.movies.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.movies.domain.model.MovieShort
import com.example.movies.ui.search.SearchMovieState
import com.example.movies.ui.search.SearchMovieViewModel

@Composable
fun SearchMovieScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchMovieViewModel = viewModel(),
    onItemClick: (String) -> Unit = {}
) {
    val viewModelState by searchViewModel.uiState.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        SearchHeader(onSearchClick = { query ->
            if (query.isNotBlank()) {
                searchViewModel.searchMovies(query)
            }
        })

        when (val state = viewModelState) {
            is SearchMovieState.Empty -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        "Search for a movie above",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            is SearchMovieState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            "Error: ${state.message}",
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { searchViewModel.searchMovies("") }) {
                            Text("Try Again")
                        }
                    }
                }
            }
            is SearchMovieState.Loaded -> {
                if (state.movies.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            "No movies found. Try a different search term.",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                } else {
                    MovieList(movies = state.movies, onItemClick = onItemClick)
                }
            }
            is SearchMovieState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun SearchHeader(
    onSearchClick: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search movies") },
            modifier = Modifier.weight(1f),
            singleLine = true
        )

        Button(
            onClick = { onSearchClick(searchText) },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text("Search")
        }
    }
}

@Composable
fun MovieList(
    movies: List<MovieShort>,
    onItemClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(movies) { movie ->
            MovieItem(movie = movie, onItemClick = onItemClick)
        }
    }
}

@Composable
fun MovieItem(
    movie: MovieShort,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onItemClick(movie.id) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = "Movie poster",
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp)
            )

            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchMovieScreenPreview() {
    SearchMovieScreen()
}