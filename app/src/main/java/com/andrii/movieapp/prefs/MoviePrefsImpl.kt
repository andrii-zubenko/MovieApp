package com.andrii.movieapp.prefs

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey

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
    override fun getLastUpdatedDate(): Flow<String> = dataStore.data.catch {
        emit(emptyPreferences())
    }.map {
        it[LAST_UPDATED_TIMESTAMP_KEY] ?: ""
    }

    override suspend fun setLastUpdatedDate() {
        val currentTimestamp = System.currentTimeMillis()

        dataStore.edit {
            it[LAST_UPDATED_TIMESTAMP_KEY] = currentTimestamp.toString()
        }
    }

    companion object {
        private val LAST_UPDATED_TIMESTAMP_KEY = stringPreferencesKey("last_updated")
    }

}
