package com.example.movies.data

import com.example.movies.domain.model.MovieDetail
import com.example.movies.domain.model.MovieShort
import com.example.movies.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MockMovieRepository : MovieRepository {
    override suspend fun searchMovie(search: String): List<MovieShort> {
        return withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            listOf(
                MovieShort("1", "Movie 1", "https://poster.jpg/1"),
                MovieShort("2", "Movie 2", "https://example.com/poster2.jpg"),
                MovieShort("3", "Movie 3", "https://example.com/poster3.jpg"),
                MovieShort("4", "Movie 4", "https://example.com/poster4.jpg"),
                MovieShort("5", "Movie 5", "https://example.com/poster5.jpg"),
                MovieShort("6", "Movie 6", "https://example.com/poster6.jpg"),
                MovieShort("7", "Movie 7", "https://example.com/poster7.jpg"),
                MovieShort("8", "Movie 8", "https://example.com/poster8.jpg"),
                MovieShort("9", "Movie 9", "https://example.com/poster9.jpg"),
                MovieShort("10", "Movie 10", "https://example.com/poster10.jpg"),
                MovieShort("11", "Movie 11", "https://example.com/poster11.jpg"),
                MovieShort("12", "Movie 12", "https://example.com/poster12.jpg"),
                MovieShort("13", "Movie 13", "https://example.com/poster13.jpg"),
                MovieShort("14", "Movie 14", "https://example.com/poster14.jpg"),
                MovieShort("15", "Movie 15", "https://example.com/poster15.jpg"),
                MovieShort("16", "Movie 16", "https://example.com/poster16.jpg"),
                MovieShort("17", "Movie 17", "https://example.com/poster17.jpg"),
                MovieShort("18", "Movie 18", "https://example.com/poster18.jpg"),
                MovieShort("19", "Movie 19", "https://example.com/poster19.jpg"),
                MovieShort("20", "Movie 20", "https://example.com/poster20.jpg")
            )
        }
    }

    override suspend fun getMovieDetail(movieId: String): Result<MovieDetail> {
        return withContext(Dispatchers.IO) {
            Thread.sleep(1500)
            Result.success(
                MovieDetail(
                    id = movieId,
                    title = "Rogue One: A Star Wars Story",
                    posterUrl = "https://m.media-amazon.com/images/M/MV5BMjEwMzMxODIzOV5BMl5BanBnXkFtZTgwNzg3OTAzMDI@._V1_SX300.jpg",
                    year = "2016",
                    rated = "PG-13",
                    runtime = "133 min",
                    genre = "Action, Adventure, Sci-Fi",
                    director = "Gareth Edwards",
                    writer = "Chris Weitz, Tony Gilroy, John Knoll",
                    actors = "Felicity Jones, Diego Luna, Alan Tudyk",
                    plot = "In a time of conflict, a group of unlikely heroes band together on a mission to steal the plans to the Death Star, the Empire's ultimate weapon of destruction.",
                    awards = "Nominated for 2 Oscars. 24 wins & 80 nominations total",
                    rating = "7.8"
                )
            )
        }
    }
}