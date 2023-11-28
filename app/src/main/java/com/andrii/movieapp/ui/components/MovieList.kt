package com.andrii.movieapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andrii.movieapp.sampledata.sampleMovies
import com.andrii.movieapp.ui.screens.movielist.MovieListState

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    movieListState: MovieListState.Success,
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        contentPadding = PaddingValues(all = 10.dp)
    ) {
        items(count = movieListState.movies.size) { index ->
            MoviePoster(
                movie = movieListState.movies[index],
            )
        }
    }
}

@Preview
@Composable
fun MovieListPreview() {
    MovieList(
        movieListState = MovieListState.Success(sampleMovies),
    )
}