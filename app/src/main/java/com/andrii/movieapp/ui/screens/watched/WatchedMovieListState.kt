package com.andrii.movieapp.ui.screens.watched

import com.andrii.movieapp.models.Movie

sealed class WatchedMovieListState {
    data object Loading : WatchedMovieListState()
    data class Success(val movies: List<Movie>) : WatchedMovieListState()
    data class Error(val error: Throwable) : WatchedMovieListState()
}