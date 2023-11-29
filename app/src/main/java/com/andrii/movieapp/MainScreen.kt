package com.andrii.movieapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.andrii.movieapp.ui.nav.MovieBottomNavigation
import com.andrii.movieapp.ui.nav.MovieList
import com.andrii.movieapp.ui.nav.Navigation
import com.andrii.movieapp.ui.nav.WatchLater
import com.andrii.movieapp.ui.nav.Watched

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        MovieList,
        WatchLater,
        Watched
    )

    Scaffold(
        bottomBar = {
            MovieBottomNavigation(navController, bottomNavigationItems)
        },
    ) { paddingValues ->
        Navigation(
            navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}
