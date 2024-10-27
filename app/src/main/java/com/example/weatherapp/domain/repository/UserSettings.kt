package com.example.weatherapp.domain.repository

import com.example.weatherapp.presentation.setting_screen.model.Language
import kotlinx.coroutines.flow.Flow

interface UserSettings {
    suspend fun setLanguage(language: Language)
    fun getLanguageCode(): Flow<String>
    fun getLanguageId(): Flow<Int>
}