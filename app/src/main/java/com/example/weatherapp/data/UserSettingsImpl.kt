package com.example.weatherapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.weatherapp.domain.repository.UserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class UserSettingsImpl(private val context: Context) : UserSettings {

    private val LANGUAGE_KEY = stringPreferencesKey("language_key")

    override suspend fun setLanguage(languageCode: String) {
        context.dataStore.edit { settings ->
            settings[LANGUAGE_KEY] = languageCode

        }
    }

    override fun getLanguage(): Flow<String> {
        return context.dataStore.data
            .map { preferences ->
                preferences[LANGUAGE_KEY] ?: "en"
            }
    }
}
