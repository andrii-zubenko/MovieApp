package com.andrii.movieapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andrii.movieapp.ui.animations.shimmerEffect

@Composable
fun MoviePosterLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(330.dp)
            .fillMaxWidth()
            .shimmerEffect(),
    ) {
    }
}

@Composable
@Preview
fun MoviePosterLoadingPreview() {
    MoviePosterLoading()
}