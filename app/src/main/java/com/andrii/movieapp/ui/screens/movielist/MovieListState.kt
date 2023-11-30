package com.andrii.movieapp.ui.screens.movielist

import com.andrii.movieapp.models.Movie

sealed class MovieListState {
    data object Loading : MovieListState()
    data class Success(val movies: List<Movie>, val lastUpdatedDate: String) : MovieListState()
    data class Error(val error: Throwable) : MovieListState()
}
