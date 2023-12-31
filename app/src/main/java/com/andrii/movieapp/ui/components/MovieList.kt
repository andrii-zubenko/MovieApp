package com.andrii.movieapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andrii.movieapp.R
import com.andrii.movieapp.sampledata.sampleMovies
import com.andrii.movieapp.ui.screens.populramovielist.PopularMovieListState
import com.andrii.movieapp.utils.formatTimestamp
import kotlinx.coroutines.launch

private const val MOVIE_LIST_LAST_UPDATED_DATE_TAG = "MOVIE_LIST_LAST_UPDATED_DATE_TAG"
private const val MOVIE_LIST_DATA_SOURCE_TAG = "MOVIE_LIST_DATA_SOURCE_TAG"
private const val MOVIE_POSTER_TAG = "MOVIE_POSTER_TAG"

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    movieListState: PopularMovieListState.Success,
    screenOrientation: Int,
    onMovieRowTap: (movieId: Long) -> Unit,
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
    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center

        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(
                        R.string.last_live_update_on,
                        formatTimestamp(movieListState.lastUpdatedDate)
                    ),
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.testTag(MOVIE_LIST_LAST_UPDATED_DATE_TAG)
                )
                Text(
                    text = if (movieListState.fromApi) {
                        stringResource(R.string.data_loaded_from_api)
                    } else {
                        stringResource(R.string.data_loaded_from_local_storage)
                    },
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.testTag(MOVIE_LIST_DATA_SOURCE_TAG)
                )
            }
        }
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
                    bottom = 70.dp
                )
            ) {
                items(count = movieListState.movies.size) { index ->
                    val movieId = movieListState.movies[index].id!!
                    if (!refreshing) {
                        MoviePoster(
                            movie = movieListState.movies[index],
                            onTap = { onMovieRowTap(movieId) },
                            modifier = Modifier.testTag("${MOVIE_POSTER_TAG}_$index")
                        )
                    }
                }
            }
            PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListPreviewPortrait() {
    MovieList(
        movieListState = PopularMovieListState.Success(
            movies = sampleMovies,
            lastUpdatedDate = 1701401296104,
            fromApi = false
        ),
        screenOrientation = Configuration.ORIENTATION_PORTRAIT,
        onMovieRowTap = {},
        onPulRefresh = {}
    )
}

@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p, widthDp = 864, heightDp = 432)
@Composable
fun MovieListPreviewLandscape() {
    MovieList(
        movieListState = PopularMovieListState.Success(
            movies = sampleMovies,
            lastUpdatedDate = 1701401296104,
            fromApi = false
        ),
        screenOrientation = Configuration.ORIENTATION_LANDSCAPE,
        onMovieRowTap = {},
        onPulRefresh = {}
    )
}
