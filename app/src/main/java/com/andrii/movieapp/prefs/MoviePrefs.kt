package com.andrii.movieapp.prefs

import kotlinx.coroutines.flow.Flow

interface MoviePrefs {
    fun getLastUpdatedDate(): Flow<String>
    fun getIsFromApiValue(): Flow<Boolean>

    suspend fun setLastUpdatedDate()
    suspend fun setIsFromApiValue(isFromApi: Boolean)
}
