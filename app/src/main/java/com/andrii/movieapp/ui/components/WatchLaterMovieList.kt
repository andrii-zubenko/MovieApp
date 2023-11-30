package com.andrii.movieapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andrii.movieapp.ui.screens.watchlater.WatchLaterMovieListState

@Composable
fun WatchLaterMovieList(
    modifier: Modifier = Modifier,
    movieListState: WatchLaterMovieListState.Success,
    screenOrientation: Int,
    onMoviePosterTap: (movieIndex: Int) -> Unit,
) {
    if (movieListState.movies.isEmpty()) {
        Text(text = "No movies")
    } else {
        LazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            columns = if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
                GridCells.Fixed(2)
            } else {
                GridCells.Fixed(4)
            },
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
            contentPadding = PaddingValues(
                start = 10.dp,
                end = 10.dp,
                top = 10.dp,
                bottom = 70.dp,
            )
        ) {
            items(count = movieListState.movies.size) { index ->
                MoviePoster(
                    movie = movieListState.movies[index],
                    onTap = { onMoviePosterTap(index) }
                )
            }
        }
    }
}