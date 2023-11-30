package com.andrii.movieapp.ui.screens.populramovielist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.andrii.movieapp.ui.components.MovieList
import com.andrii.movieapp.ui.components.MovieListError
import com.andrii.movieapp.ui.components.MovieListLoading

@Composable
fun MovieListScreen(
    viewModel: PopularMovieListViewModel,
    screenOrientation: Int,
    onMoviePosterTap: (movieId: Long) -> Unit,
) {

    val state by viewModel.uiState.collectAsState()

    when (state) {
        is PopularMovieListState.Loading -> MovieListLoading()

        is PopularMovieListState.Success -> MovieList(
            movieListState = state as PopularMovieListState.Success,
            screenOrientation = screenOrientation,
            onMovieRowTap = onMoviePosterTap,
            onPulRefresh = viewModel::fetchMovies,
        )

        is PopularMovieListState.Error -> MovieListError(
            error = (state as PopularMovieListState.Error).error,
            onPulRefresh = viewModel::fetchMovies,
        )
    }
}
