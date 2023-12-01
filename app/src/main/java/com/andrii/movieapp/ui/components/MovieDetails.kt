package com.andrii.movieapp.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.andrii.movieapp.models.WatchedStatus
import com.andrii.movieapp.sampledata.sampleMovies

@Composable
fun MovieDetails(
    movie: Movie,
    modifier: Modifier = Modifier,
    onAddToWatchLaterTap: () -> Unit,
    onAddToWatchedTap: () -> Unit,
) {

    var watchedStatus by rememberSaveable { mutableStateOf(movie.watchedStatus) }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .padding(
                PaddingValues(
                    start = 10.dp,
                    end = 10.dp,
                    top = 10.dp,
                    bottom = 70.dp,
                )
            )
            .verticalScroll(enabled = true, state = rememberScrollState())
    ) {
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
            text = stringResource(R.string.overview),
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

        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Button(
                onClick = {
                    watchedStatus = WatchedStatus.WATCHED.statusString
                    onAddToWatchedTap()
                    Toast.makeText(context, "Added to Watched", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                modifier = Modifier.width(180.dp),
                enabled = watchedStatus != WatchedStatus.WATCHED.statusString
            ) {
                Text(
                    text = if (watchedStatus != WatchedStatus.WATCHED.statusString) {
                        stringResource(R.string.watched)
                    } else {
                        stringResource(R.string.watched_checkmark)
                    },
                    color = MaterialTheme.colorScheme.onSurface
                )

            }

            Button(
                onClick = {
                    watchedStatus = WatchedStatus.WATCH_LATER.statusString
                    onAddToWatchLaterTap()
                    Toast.makeText(context, "Added to Watch Later", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                modifier = Modifier.width(150.dp),
                enabled = watchedStatus != WatchedStatus.WATCH_LATER.statusString
            )
            {
                Text(
                    text = if (watchedStatus != WatchedStatus.WATCH_LATER.statusString) {
                        stringResource(R.string.watch_later_button)
                    } else {
                        stringResource(R.string.watch_later_checkmark)
                    },
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
