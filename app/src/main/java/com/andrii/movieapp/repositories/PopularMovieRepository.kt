package com.andrii.movieapp.repositories

import com.andrii.movieapp.models.Movie
import kotlinx.coroutines.flow.Flow

interface PopularMovieRepository {
    val popularMovies: Flow<List<Movie>>

    suspend fun fetchMovies()
    fun getMovie(id: Long): Movie?
    fun getLastUpdatedDate(): Long
    fun isFromApi(): Boolean
    suspend fun addToWatchLater(movie: Movie)
    suspend fun addToWatched(movie: Movie)
}
