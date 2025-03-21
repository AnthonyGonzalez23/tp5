package com.example.movies.data.repository

import com.example.movies.data.api.OmdbApi
import com.example.movies.data.api.model.OmdbMovieDetail
import com.example.movies.data.api.model.OmdbMovieShort
import com.example.movies.domain.model.MovieDetail
import com.example.movies.domain.model.MovieShort
import com.example.movies.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OmdbMovieRepository : MovieRepository {
    companion object {
        private const val BASE_URL = "https://www.omdbapi.com"
        private const val API_KEY = "66d4f4a8"
    }

    private val api: OmdbApi

    init {
        val okhttpClient = OkHttpClient.Builder().build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(OmdbApi::class.java)
    }

    override suspend fun searchMovie(search: String): List<MovieShort> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.searchMovie(search, API_KEY)

                if (response != null && response.movies != null && response.response?.equals("True", ignoreCase = true) == true) {
                    val movieList = response.movies.filter {
                        it.imdbId.isNotEmpty() && it.title.isNotEmpty()
                    }.map {
                        it.toMovieShort()
                    }

                    if (movieList.isNotEmpty()) {
                        return@withContext movieList
                    }
                }

                // If we get here, something went wrong or the search returned no results
                emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }

    override suspend fun getMovieDetail(movieId: String): Result<MovieDetail> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getMovieDetail(movieId, API_KEY)
                Result.success(response.toMovieDetail())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    private fun OmdbMovieShort.toMovieShort() =
        MovieShort(
            id = this.imdbId,
            title = this.title,
            posterUrl = this.poster
        )

    private fun OmdbMovieDetail.toMovieDetail() =
        MovieDetail(
            id = this.imdbId,
            title = this.title,
            posterUrl = this.poster,
            year = this.year,
            rated = this.rated,
            runtime = this.runtime,
            genre = this.genre,
            director = this.director,
            writer = this.writer,
            actors = this.actors,
            plot = this.plot,
            awards = this.awards,
            rating = this.imdbRating
        )
}