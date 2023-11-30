package com.andrii.movieapp.repositories.saved

import com.andrii.movieapp.models.Movie
import kotlinx.coroutines.flow.Flow

interface SavedMovieRepository {
    val watchedMovies: Flow<List<Movie>>
    val watchLaterMovies: Flow<List<Movie>>

    suspend fun fetchWatchedMovies()
    suspend fun fetchWatchLaterMovies()
}