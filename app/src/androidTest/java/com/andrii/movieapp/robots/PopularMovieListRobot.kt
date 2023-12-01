package com.andrii.movieapp.robots

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.andrii.movieapp.MainActivity

private const val MOVIE_LIST_LAST_UPDATED_DATE_TAG = "MOVIE_LIST_LAST_UPDATED_DATE_TAG"
private const val MOVIE_LIST_DATA_SOURCE_TAG = "MOVIE_LIST_DATA_SOURCE_TAG"
private const val MOVIE_POSTER_TAG = "MOVIE_POSTER_TAG"

fun movieList(
    composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    func: PopularMovieListRobot.() -> Unit
) = PopularMovieListRobot(composeTestRule).apply { func() }

class PopularMovieListRobot(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {
    private val lastUpdatedDateText =
        composeTestRule.onNodeWithTag(MOVIE_LIST_LAST_UPDATED_DATE_TAG)
    private val dataSourceText =
        composeTestRule.onNodeWithTag(MOVIE_LIST_DATA_SOURCE_TAG)
    private val firstMoviePoster =
        composeTestRule.onNodeWithTag("${MOVIE_POSTER_TAG}_0")

    @OptIn(ExperimentalTestApi::class)
    fun verifyLastUpdatedDateTextDisplayed() {
        composeTestRule.waitUntilAtLeastOneExists(
            hasTestTag(MOVIE_LIST_LAST_UPDATED_DATE_TAG),
            timeoutMillis = 5000
        )
        lastUpdatedDateText.assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    fun verifyDataSourceTextDisplayed() {
        composeTestRule.waitUntilAtLeastOneExists(
            hasTestTag(MOVIE_LIST_DATA_SOURCE_TAG),
            timeoutMillis = 5000
        )
        dataSourceText.assertIsDisplayed()
    }

    fun tapOnFirstMoviePoster() {
        firstMoviePoster.performClick()
    }
}
