package com.example.weatherapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserSettings {
   suspend fun setLanguage(languageCode : String)
    fun getLanguage() : Flow<String>
}