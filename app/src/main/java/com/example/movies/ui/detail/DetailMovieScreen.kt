package com.example.movies.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.movies.R
import com.example.movies.domain.model.MovieDetail
import kotlin.math.roundToInt

@Composable
fun DetailMovieScreen(
    movieId: String,
    modifier: Modifier = Modifier,
    detailViewModel: DetailMovieViewModel = viewModel()
) {
    val viewModelState by detailViewModel.uiState.collectAsState()

    LaunchedEffect(movieId) {
        detailViewModel.loadMovieDetail(movieId)
    }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        when (val state = viewModelState) {
            is DetailMovieState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is DetailMovieState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                }
            }
            is DetailMovieState.Loaded -> {
                MovieDetailContent(
                    movie = state.movie,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
fun MovieDetailContent(
    movie: MovieDetail,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        AsyncImage(
            model = movie.posterUrl,
            contentDescription = "Movie poster",
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(300.dp)
                .padding(bottom = 16.dp)
        )

        RatingStars(
            rating = movie.rating,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        InfoSection(
            title = "Year",
            value = movie.year
        )

        InfoSection(
            title = "Rated",
            value = movie.rated
        )

        InfoSection(
            title = "Runtime",
            value = movie.runtime
        )

        InfoSection(
            title = "Genre",
            value = movie.genre
        )

        InfoSection(
            title = "Director",
            value = movie.director
        )

        InfoSection(
            title = "Writer",
            value = movie.writer
        )

        InfoSection(
            title = "Actors",
            value = movie.actors
        )

        InfoSection(
            title = "Awards",
            value = movie.awards
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Plot",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )

        Text(
            text = movie.plot,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun InfoSection(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$title: ",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun RatingStars(
    rating: String,
    modifier: Modifier = Modifier
) {
    val ratingValue = rating.toFloatOrNull() ?: 0f
    val fullStars = (ratingValue / 2).roundToInt()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Rating: ",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "⭐".repeat(fullStars),
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = " ($rating/10)",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}