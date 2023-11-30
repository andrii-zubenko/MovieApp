package com.andrii.movieapp.repositories.popular

import com.andrii.movieapp.models.Movie
import kotlinx.coroutines.flow.Flow

interface PopularMovieRepository {
    val popularMovies: Flow<List<Movie>>

    suspend fun fetchMovies()
    fun getMovie(index: Int): Movie?
    fun getLastUpdatedDate(): String
    suspend fun addToWatchLater(movie: Movie)
    suspend fun addToWatched(movie: Movie)
}
