package com.andrii.movieapp.prefs

import kotlinx.coroutines.flow.Flow

interface MoviePrefs {
    fun getLastUpdatedDate(): Flow<Long>
    fun getIsFromApiValue(): Flow<Boolean>

    suspend fun setLastUpdatedDate(timeStamp: Long)
    suspend fun setIsFromApiValue(isFromApi: Boolean)
}
