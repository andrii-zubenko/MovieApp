package com.andrii.movieapp.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.andrii.movieapp.MainActivity
import com.andrii.movieapp.robots.movieDetails
import com.andrii.movieapp.robots.movieList
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MovieDetailsScreenTests {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var composeTestRule =
        createAndroidComposeRule<MainActivity>()

    @Test
    fun givenUserIsOnPopularMovieListScreenLastUpdatedDateAndDataSourceIsDisplayed() {
        movieList(composeTestRule) {
            verifyLastUpdatedDateTextDisplayed()
            verifyDataSourceTextDisplayed()
        }
    }

    @Test
    fun givenUserTapsOnFirstMoviePosterThenMovieDetailsScreenIsDisplayed() {
        movieList(composeTestRule) {
            tapOnFirstMoviePoster()
        }

        movieDetails(composeTestRule) {
            verifyUi()
        }
    }
}
