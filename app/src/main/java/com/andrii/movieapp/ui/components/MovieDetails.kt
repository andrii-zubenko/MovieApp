package com.andrii.movieapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
) {
    LazyColumn(modifier = modifier) {
        item {
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
        }
        item {
            Text(
                text = movie.title ?: "",
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMovieDetails() {
    MovieDetails(
        movie = sampleMovies.first(),
        modifier = Modifier,
    )
}
