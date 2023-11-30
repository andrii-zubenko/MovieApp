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
import com.andrii.movieapp.ui.screens.popularmoviedetails.MovieDetailsScreen
import com.andrii.movieapp.ui.screens.populramovielist.MovieListScreen
import com.andrii.movieapp.ui.screens.savedmoviedetails.SavedMovieDetailsScreen
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
                    navController.navigate("${PopularMovieDetails.route}/$movieIndex")
                },
            )
        }

        composable(
            route = PopularMovieDetails.routeWithArg,
            arguments = listOf(navArgument(PopularMovieDetails.movieIndexArg) {
                type = NavType.IntType
            }),
        ) { backStackEntry ->
            val movieIndex = backStackEntry.arguments!!.getInt(PopularMovieDetails.movieIndexArg)
            MovieDetailsScreen(
                movieIndex = movieIndex,
                viewModel = hiltViewModel(),
                onNavigateUp = { navController.navigateUp() },
            )
        }

        composable(
            route = SavedMovieDetails.routeWithArg,
            arguments = listOf(
                navArgument(SavedMovieDetails.movieIndexArg) { type = NavType.IntType },
                navArgument(SavedMovieDetails.navigatedFromScreenArg) { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            val movieIndex = backStackEntry.arguments!!.getInt(SavedMovieDetails.movieIndexArg)
            val navigatedFromScreen =
                backStackEntry.arguments!!.getString(SavedMovieDetails.navigatedFromScreenArg)
            SavedMovieDetailsScreen(
                movieIndex = movieIndex,
                viewModel = hiltViewModel(),
                onNavigateUp = { navController.navigateUp() },
                navigatedFromScreen = navigatedFromScreen ?: "",
            )
        }

        composable(WatchLater.route) {
            WatchLaterScreen(
                viewModel = hiltViewModel(),
                screenOrientation = screenOrientation,
                onMoviePosterTap = { movieIndex ->
                    navController.navigate(
                        "${SavedMovieDetails.route}/$movieIndex/${WatchLater.route}"
                    )
                },
            )
        }

        composable(Watched.route) {
            WatchedMovieListScreen(
                viewModel = hiltViewModel(),
                screenOrientation = screenOrientation,
                onMoviePosterTap = { movieIndex ->
                    navController.navigate(
                        "${SavedMovieDetails.route}/$movieIndex/${Watched.route}"
                    )
                },
            )
        }
    }
}
