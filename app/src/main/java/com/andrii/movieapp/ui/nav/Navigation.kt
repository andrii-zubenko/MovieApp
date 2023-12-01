package com.andrii.movieapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.andrii.movieapp.ui.screens.moviedetails.MovieDetailsScreen
import com.andrii.movieapp.ui.screens.populramovielist.MovieListScreen
import com.andrii.movieapp.ui.screens.watched.WatchedMovieListScreen
import com.andrii.movieapp.ui.screens.watchlater.WatchLaterScreen

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val screenOrientation = LocalConfiguration.current.orientation

    NavHost(navController = navController, startDestination = MovieList.route) {
        composable(MovieList.route) {
            MovieListScreen(
                viewModel = hiltViewModel(),
                screenOrientation = screenOrientation,
                onMoviePosterTap = { movieIndex ->
                    navController.navigate("${MovieDetails.route}/$movieIndex")
                },
            )
        }

        composable(
            route = MovieDetails.routeWithArg,
            arguments = listOf(navArgument(MovieDetails.movieIdArg) {
                type = NavType.LongType
            }),
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments!!.getLong(MovieDetails.movieIdArg)
            MovieDetailsScreen(
                movieId = movieId,
                viewModel = hiltViewModel(),
                onNavigateUp = { navController.navigateUp() },
            )
        }


        composable(WatchLater.route) {
            WatchLaterScreen(
                viewModel = hiltViewModel(),
                screenOrientation = screenOrientation,
                onMoviePosterTap = { movieId ->
                    navController.navigate(
                        "${MovieDetails.route}/$movieId"
                    )
                },
            )
        }

        composable(Watched.route) {
            WatchedMovieListScreen(
                viewModel = hiltViewModel(),
                screenOrientation = screenOrientation,
                onMoviePosterTap = { movieId ->
                    navController.navigate(
                        "${MovieDetails.route}/$movieId"
                    )
                },
            )
        }
    }
}
