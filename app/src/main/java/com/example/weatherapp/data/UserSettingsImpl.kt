package com.example.weatherapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.weatherapp.R
import com.example.weatherapp.domain.repository.UserSettings
import com.example.weatherapp.presentation.setting_screen.model.Language
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class UserSettingsImpl(private val context: Context) : UserSettings {

    private val LANGUAGE_CODE = stringPreferencesKey("language_code")
    private val LANGUAGE_ID = intPreferencesKey("language_id")

    override suspend fun setLanguage(language: Language) {
        context.dataStore.edit { settings ->
            settings[LANGUAGE_CODE] = language.languageCode
            settings[LANGUAGE_ID] = language.languageId
        }
    }

    override fun getLanguageCode(): Flow<String> {
        return context.dataStore.data
            .map { preferences ->
                preferences[LANGUAGE_CODE] ?: "en"
            }
    }

    override fun getLanguageId(): Flow<Int> {
        return context.dataStore.data
            .map { preferences ->
                preferences[LANGUAGE_ID] ?: R.string.en
            }
    }
}
