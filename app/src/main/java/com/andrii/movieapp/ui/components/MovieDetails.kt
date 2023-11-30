package com.andrii.movieapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andrii.movieapp.R
import com.andrii.movieapp.TMDB_BASE_URL
import com.andrii.movieapp.models.Movie
import com.andrii.movieapp.sampledata.sampleMovies

@Composable
fun MovieDetails(
    movie: Movie,
    modifier: Modifier,
    onAddToWatchLaterTap: () -> Unit,
    onAddToWatchedTap: () -> Unit,
) {
    Column(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(TMDB_BASE_URL + movie.backdropPath)
                .placeholder(R.drawable.empty_large_poster)
                .build(),
            modifier = Modifier
                .fillMaxWidth()
                .height(285.dp),
            contentDescription = stringResource(R.string.movie_large_poster),
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = movie.title ?: "",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Text(
                text = "${movie.voteAverage} / 10",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(R.string.released, movie.releaseDate ?: ""),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }

        Text(
            text = "Overview",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = movie.overview ?: "",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        if (!movie.addedToWatched) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        onAddToWatchedTap()
                    }
            ) {
                Text(
                    text = "add to watched",
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }

        if (!movie.addedToWatchLater) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        onAddToWatchLaterTap()
                    }
            ) {
                Text(
                    text = "add to watch later",
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMovieDetails() {
    MovieDetails(
        movie = sampleMovies.first(),
        modifier = Modifier,
        onAddToWatchLaterTap = {},
        onAddToWatchedTap = {},
    )
}
