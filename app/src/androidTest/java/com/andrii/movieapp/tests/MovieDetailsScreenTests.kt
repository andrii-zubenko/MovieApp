package com.andrii.movieapp.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.andrii.movieapp.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MovieDetailsScreenTests {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityScenarioRule =
        createAndroidComposeRule<MainActivity>()


    @Test
    fun movieDetails() {
        Thread.sleep(10000)
    }

}