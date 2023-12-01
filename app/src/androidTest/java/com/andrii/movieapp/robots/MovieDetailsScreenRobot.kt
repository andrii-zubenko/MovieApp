package com.andrii.movieapp.robots

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.andrii.movieapp.MainActivity

private const val MOVIE_LARGE_POSTER_TAG = "MOVIE_LARGE_POSTER_TAG"
private const val MOVIE_TITLE = "MOVIE_TITLE"
private const val MOVIE_RATING = "MOVIE_RATING"
private const val MOVIE_RELEASED_DATE = "MOVIE_RELEASED_DATE"
private const val MOVIE_OVERVIEW_TITLE = "MOVIE_OVERVIEW_TITLE"
private const val MOVIE_OVERVIEW_BODY = "MOVIE_OVERVIEW_BODY"

fun movieDetails(
    composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    func: MovieDetailsScreenRobot.() -> Unit
) = MovieDetailsScreenRobot(composeTestRule).apply { func() }
class MovieDetailsScreenRobot(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {

    fun verifyUi() {
        composeTestRule.onNodeWithTag(MOVIE_LARGE_POSTER_TAG).assertExists()
        composeTestRule.onNodeWithTag(MOVIE_TITLE).assertExists()
        composeTestRule.onNodeWithTag(MOVIE_RATING).assertExists()
        composeTestRule.onNodeWithTag(MOVIE_RELEASED_DATE).assertExists()
        composeTestRule.onNodeWithTag(MOVIE_OVERVIEW_TITLE).assertExists()
        composeTestRule.onNodeWithTag(MOVIE_OVERVIEW_BODY).assertExists()
    }
}
