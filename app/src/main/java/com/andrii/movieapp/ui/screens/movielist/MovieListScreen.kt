package com.andrii.movieapp.ui.screens.movielist

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.andrii.movieapp.ui.components.MovieList

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel
) {

    val state by viewModel.uiState.collectAsState()

    when (state) {
        is MovieListState.Loading -> Text(text = "Loading")


        is MovieListState.Success -> MovieList(
            movieListState = state as MovieListState.Success,
        )

        is MovieListState.Error -> Text(
            text = "Error: ${(state as MovieListState.Error).error}"
        )

    }
}
