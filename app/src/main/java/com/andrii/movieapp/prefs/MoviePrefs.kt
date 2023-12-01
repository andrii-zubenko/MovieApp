package com.andrii.movieapp.prefs

import kotlinx.coroutines.flow.Flow

interface MoviePrefs {
    fun getLastUpdatedDate(): Flow<String>

    suspend fun setLastUpdatedDate()
}
