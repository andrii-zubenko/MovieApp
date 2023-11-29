package com.andrii.movieapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.andrii.movieapp.ui.screens.moviedetails.MovieDetailsScreen
import com.andrii.movieapp.ui.screens.movielist.MovieListScreen
import com.andrii.movieapp.ui.screens.watched.WatchedScreen
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
                onMovieRowTap = { movieIndex ->
                    navController.navigate("${MovieDetails.route}/$movieIndex")
                },
            )
        }

        composable(
            route = MovieDetails.routeWithArg,
            arguments = listOf(navArgument(MovieDetails.movieIndexArg) { type = NavType.IntType }),
        ) { backStackEntry ->
            val movieIndex = backStackEntry.arguments!!.getInt(MovieDetails.movieIndexArg)
            MovieDetailsScreen(
                movieIndex = movieIndex,
                viewModel = hiltViewModel(),
                onNavigateUp = { navController.navigateUp() },
            )
        }

        composable(WatchLater.route) {
            WatchLaterScreen()
        }

        composable(Watched.route) {
            WatchedScreen()
        }
    }
}
