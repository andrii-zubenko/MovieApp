package com.andrii.movieapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andrii.movieapp.sampledata.sampleMovies
import com.andrii.movieapp.ui.screens.movielist.MovieListState
import kotlinx.coroutines.launch

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    movieListState: MovieListState.Success,
    screenOrientation: Int,
    onMovieRowTap: (movieIndex: Int) -> Unit,
    onPulRefresh: () -> Unit
) {
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        onPulRefresh()
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    Box(Modifier.pullRefresh(state)) {
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
                if (!refreshing) {
                    MoviePoster(
                        movie = movieListState.movies[index],
                        onTap = { onMovieRowTap(index) }
                    )
                }
            }
        }
        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListPreviewPortrait() {
    MovieList(
        movieListState = MovieListState.Success(sampleMovies),
        screenOrientation = Configuration.ORIENTATION_PORTRAIT,
        onMovieRowTap = {},
        onPulRefresh = {}
    )
}

@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p, widthDp = 864, heightDp = 432)
@Composable
fun MovieListPreviewLandscape() {
    MovieList(
        movieListState = MovieListState.Success(sampleMovies),
        screenOrientation = Configuration.ORIENTATION_LANDSCAPE,
        onMovieRowTap = {},
        onPulRefresh = {}
    )
}