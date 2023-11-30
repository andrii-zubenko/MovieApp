package com.andrii.movieapp.repositories.watchlater

import com.andrii.movieapp.models.Movie
import kotlinx.coroutines.flow.Flow

interface WatchLaterMovieRepository {
    val watchLaterMovies: Flow<List<Movie>>

    suspend fun fetchMovies()
}