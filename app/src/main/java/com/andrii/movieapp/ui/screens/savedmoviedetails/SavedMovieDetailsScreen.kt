package com.andrii.movieapp.ui.screens.savedmoviedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.andrii.movieapp.R
import com.andrii.movieapp.models.Movie
import com.andrii.movieapp.repositories.popular.PopularMovieRepository
import com.andrii.movieapp.repositories.saved.SavedMovieRepository
import com.andrii.movieapp.sampledata.sampleMovies
import com.andrii.movieapp.ui.components.MovieDetails
import com.andrii.movieapp.ui.nav.WatchLater
import com.andrii.movieapp.ui.nav.Watched
import kotlinx.coroutines.flow.Flow

@Composable
fun SavedMovieDetailsScreen(
    movieIndex: Int,
    viewModel: SavedMovieDetailsViewModel,
    onNavigateUp: () -> Unit,
    navigatedFromScreen: String,
) {

    val movie = if (navigatedFromScreen == Watched.route) {
        viewModel.getSelectedWatchedMovie(movieIndex)
        viewModel.selectedWatchedMovie.value
    } else if (navigatedFromScreen == WatchLater.route) {
        viewModel.getSelectedWatchLaterMovie(movieIndex)
        viewModel.selectedWatchLaterMovie.value
    } else {
        null
    }

    Column {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(movie!!.title.orEmpty()) },
                    navigationIcon = {
                        IconButton(onClick = {
                            onNavigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_button)
                            )
                        }
                    }
                )
            },
            content = { paddingValues ->
                MovieDetails(
                    movie = movie!!,
                    modifier = Modifier.padding(paddingValues),
                    onAddToWatchLaterTap = {
                        viewModel.addToWatchLater()
                    },
                    onAddToWatchedTap = {
                        viewModel.addToWatched()
                    }
                )
            }
        )
    }
}

@Composable
@Preview
fun PreviewMovieDetailsScreen() {
    SavedMovieDetailsScreen(
        movieIndex = 0,
        viewModel = SavedMovieDetailsViewModel(
            savedMovieRepository = object : SavedMovieRepository {
                override val watchedMovies: Flow<List<Movie>>
                    get() = TODO("Not yet implemented")
                override val watchLaterMovies: Flow<List<Movie>>
                    get() = TODO("Not yet implemented")

                override suspend fun fetchWatchedMovies() {
                    TODO("Not yet implemented")
                }

                override suspend fun fetchWatchLaterMovies() {
                    TODO("Not yet implemented")
                }

                override fun getWatchedMovie(movieIndex: Int): Movie {
                    TODO("Not yet implemented")
                }

                override fun getWatchLaterMovie(movieIndex: Int): Movie {
                    TODO("Not yet implemented")
                }

            }
        ),
        onNavigateUp = {},
        navigatedFromScreen = Watched.route
    )
}
