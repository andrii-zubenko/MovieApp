package com.andrii.movieapp.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val STORE_NAME = "movie_prefs"
class MoviePrefsImpl @Inject constructor(@ApplicationContext context: Context) : MoviePrefs {
    private val Context.dataStore by preferencesDataStore(name = STORE_NAME)
    private val dataStore = context.dataStore
    override fun getLastUpdatedDate(): Flow<Long> = dataStore.data.catch {
        emit(emptyPreferences())
    }.map {
        it[LAST_UPDATED_TIMESTAMP_KEY] ?: 0L
    }

    override fun getIsFromApiValue(): Flow<Boolean> = dataStore.data.catch {
        emit(emptyPreferences())
    }.map {
        it[IS_FROM_API_KEY] ?: true
    }

    override suspend fun setLastUpdatedDate(timeStamp: Long) {
        dataStore.edit {
            it[LAST_UPDATED_TIMESTAMP_KEY] = timeStamp
        }
    }

    override suspend fun setIsFromApiValue(isFromApi: Boolean) {
        dataStore.edit {
            it[IS_FROM_API_KEY] = isFromApi
        }
    }

    companion object {
        private val LAST_UPDATED_TIMESTAMP_KEY = longPreferencesKey("last_updated")
        private val IS_FROM_API_KEY = booleanPreferencesKey("is_from_api")
    }
}
