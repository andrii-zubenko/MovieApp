package com.andrii.movieapp.ui.screens.watchlater

import com.andrii.movieapp.models.Movie

sealed class WatchLaterMovieListState {
    data object Loading : WatchLaterMovieListState()
    data class Success(val movies: List<Movie>) : WatchLaterMovieListState()
    data class Error(val error: Throwable) : WatchLaterMovieListState()
}
