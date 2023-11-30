package com.andrii.movieapp.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.ui.graphics.vector.ImageVector
import com.andrii.movieapp.R

sealed interface Destination {
    val route: String
    val icon: ImageVector
    val resourceId: Int
}

data object MovieList : Destination {
    override val route = "movie_list"
    override val icon = Icons.AutoMirrored.Filled.List
    override val resourceId = R.string.movie_list
}

data object PopularMovieDetails : Destination {
    override val route = "popular_movie_details"
    override val icon = Icons.Filled.Details
    override val resourceId = R.string.movie_details
    const val movieIndexArg = "movieIndex"
    val routeWithArg = "$route/{movieIndex}"
}

data object SavedMovieDetails : Destination {
    override val route = "saved_movie_details"
    override val icon = Icons.Filled.Details
    override val resourceId = R.string.movie_details
    const val movieIndexArg = "movieIndex"
    const val navigatedFromScreenArg = "navigatedFromScreen"
    val routeWithArg = "$route/{movieIndex}"
}

data object WatchLater : Destination {
    override val route = "watch_later"
    override val icon = Icons.Filled.WatchLater
    override val resourceId = R.string.watch_later
}

data object Watched : Destination {
    override val route = "watched"
    override val icon = Icons.Filled.DoneAll
    override val resourceId = R.string.watched
}
