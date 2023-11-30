package com.andrii.movieapp.ui.screens.populramovielist

import com.andrii.movieapp.models.Movie

sealed class PopularMovieListState {
    data object Loading : PopularMovieListState()
    data class Success(val movies: List<Movie>, val lastUpdatedDate: String) : PopularMovieListState()
    data class Error(val error: Throwable) : PopularMovieListState()
}
