package com.andrii.movieapp.ui.nav

sealed interface Destination {
    val route: String
}

data object MovieList : Destination {
    override val route = "movie_list"
}

data object MovieDetails : Destination {
    override val route = "movie_details"
    const val movieIndexArg = "movieIndex"
    val routeWithArg = "$route/{movieIndex}"
}
