package com.andrii.movieapp.ui.screens.moviedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.andrii.movieapp.repositories.MovieRepository
import com.andrii.movieapp.sampledata.sampleMovies
import com.andrii.movieapp.ui.components.MovieDetails
import kotlinx.coroutines.flow.Flow

@Composable
fun MovieDetailsScreen(
    movieIndex: Int,
    viewModel: MovieDetailsViewModel,
    onNavigateUp: () -> Unit,
) {
    viewModel.getSelectedMovie(movieIndex)
    Column {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(viewModel.selectedMovie.value?.title.orEmpty()) },
                    navigationIcon = {
                        IconButton(onClick = {
                            onNavigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_button)
                            )
                        }
                    }
                )
            },
            content = { paddingValues ->
                MovieDetails(
                    movie = viewModel.selectedMovie.value!!,
                    modifier = Modifier.padding(paddingValues),
                )
            }
        )
    }
}

@Composable
@Preview
fun PreviewMovieDetailsScreen() {
    MovieDetailsScreen(
        movieIndex = 0,
        viewModel = MovieDetailsViewModel(
            repository = object : MovieRepository {
                override val movies: Flow<List<Movie>>
                    get() = TODO("Not yet implemented")

                override suspend fun fetchMovies() {
                    // do nothing
                }

                override fun getMovie(index: Int): Movie {
                    return sampleMovies[index]
                }

                override fun getLastUpdatedDate(): String {
                    return "2021-09-01"
                }

                override suspend fun addToWatchLater(movie: Movie) {
                    // do nothing
                }

                override suspend fun addToWatched(movie: Movie) {
                    // do nothing
                }
            }
        ),
        onNavigateUp = {},
    )
}
