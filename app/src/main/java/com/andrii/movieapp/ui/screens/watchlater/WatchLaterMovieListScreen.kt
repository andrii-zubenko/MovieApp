package com.andrii.movieapp.ui.screens.watchlater

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.andrii.movieapp.ui.components.WatchLaterMovieList

@Composable
fun WatchLaterScreen(
    viewModel: WatchLaterMovieListViewModel,
    screenOrientation: Int,
    onMoviePosterTap: (movieId: Long) -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is WatchLaterMovieListState.Loading -> Text(text = "Loading...")

        is WatchLaterMovieListState.Success -> WatchLaterMovieList(
            movieListState = state as WatchLaterMovieListState.Success,
            screenOrientation = screenOrientation,
            onMoviePosterTap = onMoviePosterTap,
        )

        is WatchLaterMovieListState.Error -> Text(text = "Error")
    }
}
