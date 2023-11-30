package com.andrii.movieapp.repositories

import com.andrii.movieapp.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    val movies: Flow<List<Movie>>

    suspend fun fetchMovies()
    fun getMovie(index: Int): Movie?
    fun getLastUpdatedDate(): String
    suspend fun addToWatchLater(movie: Movie)
    suspend fun addToWatched(movie: Movie)
}
