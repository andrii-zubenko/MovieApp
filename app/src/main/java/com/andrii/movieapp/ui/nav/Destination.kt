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

data object MovieDetails : Destination {
    override val route = "movie_details"
    override val icon = Icons.Filled.Details
    override val resourceId = R.string.movie_details
    const val movieIdArg = "movieId"
    val routeWithArg = "$route/{movieId}"
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
