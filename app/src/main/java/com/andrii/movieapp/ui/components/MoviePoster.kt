package com.andrii.movieapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
fun MoviePoster(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(290.dp)
            .clickable(
                onClick = { }
            )
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(TMDB_BASE_URL + movie.posterPath)
                    .placeholder(R.drawable.emptyfilmposter)
                    .build(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                contentDescription = stringResource(R.string.movie_poster),
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = movie.title ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                )
            }
        }
    }
}

@Preview
@Composable
fun MoviePosterPreview() {
    MoviePoster(
        movie = sampleMovies[2]
    )
}
