package com.example.movies.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.domain.model.MovieShort
import androidx.compose.foundation.clickable

@Composable
fun MovieList(
    movies: List<MovieShort>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize()
    ) {
        items(movies.size) { index ->
            MovieItem(
                movie = movies[index],
                onClick = { onItemClick(movies[index].id) }
            )
            if (index < movies.size - 1) {
                Divider(modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: MovieShort,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable(onClick = onClick)
    ) {

    }
}