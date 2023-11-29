package com.andrii.movieapp.ui.screens.movielist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.andrii.movieapp.ui.components.MovieList
import com.andrii.movieapp.ui.components.MovieListError
import com.andrii.movieapp.ui.components.MovieListLoading

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    screenOrientation: Int,
    onMovieRowTap: (movieIndex: Int) -> Unit,
) {

    val state by viewModel.uiState.collectAsState()

    when (state) {
        is MovieListState.Loading -> MovieListLoading()

        is MovieListState.Success -> MovieList(
            movieListState = state as MovieListState.Success,
            screenOrientation = screenOrientation,
            onMovieRowTap = onMovieRowTap,
            onPulRefresh = viewModel::fetchMovies,
        )

        is MovieListState.Error -> MovieListError(
            error = (state as MovieListState.Error).error,
        )
    }
}
