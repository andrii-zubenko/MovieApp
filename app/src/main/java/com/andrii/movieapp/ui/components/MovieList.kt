package com.andrii.movieapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andrii.movieapp.sampledata.sampleMovies
import com.andrii.movieapp.ui.screens.movielist.MovieListState

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    movieListState: MovieListState.Success,
    screenOrientation: Int,
    onMovieRowTap: (movieIndex: Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
            GridCells.Fixed(2)
        } else {
            GridCells.Fixed(4)
        },
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        contentPadding = PaddingValues(all = 10.dp)
    ) {
        items(count = movieListState.movies.size) { index ->
            MoviePoster(
                movie = movieListState.movies[index],
                onTap = { onMovieRowTap(index) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListPreviewPortrait() {
    MovieList(
        movieListState = MovieListState.Success(sampleMovies),
        screenOrientation = Configuration.ORIENTATION_PORTRAIT,
        onMovieRowTap = {}
    )
}

@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p, widthDp = 864, heightDp = 432)
@Composable
fun MovieListPreviewLandscape() {
    MovieList(
        movieListState = MovieListState.Success(sampleMovies),
        screenOrientation = Configuration.ORIENTATION_LANDSCAPE,
        onMovieRowTap = {}
    )
}