package com.andrii.movieapp.ui.screens.moviedetails

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
import com.andrii.movieapp.repositories.PopularMovieRepository
import com.andrii.movieapp.sampledata.sampleMovies
import com.andrii.movieapp.ui.components.MovieDetails
import kotlinx.coroutines.flow.Flow

@Composable
fun MovieDetailsScreen(
    movieId: Long,
    viewModel: MovieDetailsViewModel,
    onNavigateUp: () -> Unit
) {
    viewModel.getSelectedMovie(movieId)
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
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
    MovieDetailsScreen(
        movieId = 0L,
        viewModel = MovieDetailsViewModel(
            popularMovieRepository = object : PopularMovieRepository {
                override val popularMovies: Flow<List<Movie>>
                    get() = TODO("Not yet implemented")

                override suspend fun fetchMovies() {
                    // do nothing
                }

                override fun getMovie(id: Long): Movie {
                    return sampleMovies.firstOrNull { it.id == id } ?: sampleMovies.first()
                }

                override fun getLastUpdatedDate(): Long {
                    return 1701401296104
                }

                override fun isFromApi(): Boolean {
                    TODO("Not yet implemented")
                }

                override suspend fun addToWatchLater(movie: Movie) {
                    // do nothing
                }

                override suspend fun addToWatched(movie: Movie) {
                    // do nothing
                }
            }
        ),
        onNavigateUp = {}
    )
}
