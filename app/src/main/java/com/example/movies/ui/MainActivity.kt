package com.example.movies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.movies.ui.component.SearchMovieScreen
import com.example.movies.ui.detail.DetailMovieScreen
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    @Serializable
    object SearchRoute

    @Serializable
    data class DetailRoute(val id: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavigationComponent(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun NavigationComponent(
        navController: NavHostController,
        modifier: Modifier = Modifier
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = SearchRoute
        ) {
            composable<SearchRoute> {
                SearchMovieScreen(
                    onItemClick = { movieId ->
                        navController.navigate(DetailRoute(movieId))
                    }
                )
            }
            composable<DetailRoute> { backStackEntry ->
                val detailRoute = backStackEntry.toRoute<DetailRoute>()
                DetailMovieScreen(movieId = detailRoute.id)
            }
        }
    }
}