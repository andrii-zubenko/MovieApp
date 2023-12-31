package com.andrii.movieapp.ui.screens.watched

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.andrii.movieapp.ui.components.GenericError
import com.andrii.movieapp.ui.components.LoadingScreen
import com.andrii.movieapp.ui.components.WatchedMovieList

@Composable
fun WatchedMovieListScreen(
    viewModel: WatchedMovieListViewModel,
    screenOrientation: Int,
    onMoviePosterTap: (movieId: Long) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is WatchedMovieListState.Loading -> LoadingScreen()

        is WatchedMovieListState.Success -> WatchedMovieList(
            movieListState = state as WatchedMovieListState.Success,
            screenOrientation = screenOrientation,
            onMoviePosterTap = onMoviePosterTap
        )

        is WatchedMovieListState.Error -> GenericError()
    }
}
