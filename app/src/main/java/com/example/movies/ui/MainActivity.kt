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
import com.example.movies.ui.component.SearchMovieScreen
import com.example.movies.ui.detail.DetailMovieScreen
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    @Serializable
    object SearchRoute

    @Serializable
    data class DetailRoute(val id: String)

    companion object {
        const val SEARCH_ROUTE = "search"
        const val DETAIL_ROUTE = "detail/{id}"
        const val DETAIL_ROUTE_WITH_ARGS = "detail/"
    }

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
            startDestination = SEARCH_ROUTE
        ) {
            composable(SEARCH_ROUTE) {
                SearchMovieScreen(
                    onItemClick = { movieId ->
                        navController.navigate("${DETAIL_ROUTE_WITH_ARGS}$movieId")
                    }
                )
            }
            composable(
                route = DETAIL_ROUTE,
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getString("id") ?: ""
                DetailMovieScreen(movieId = movieId)
            }
        }
    }
}