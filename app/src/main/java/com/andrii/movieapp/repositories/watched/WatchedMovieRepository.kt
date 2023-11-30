package com.andrii.movieapp.repositories.watched

import com.andrii.movieapp.models.Movie
import kotlinx.coroutines.flow.Flow

interface WatchedMovieRepository {
    val watchedMovies: Flow<List<Movie>>

    suspend fun fetchMovies()
}