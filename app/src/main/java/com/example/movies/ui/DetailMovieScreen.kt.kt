package com.example.movies.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailMovieScreen(
    movieId: String,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(movieId) {
        Log.d("TAG", "DetailMovieScreen movieId: $movieId")
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Movie Detail Screen")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Movie ID: $movieId")
    }
}