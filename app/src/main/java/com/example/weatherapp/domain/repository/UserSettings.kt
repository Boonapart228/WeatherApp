package com.example.weatherapp.domain.repository

import com.example.weatherapp.presentation.setting_screen.model.FontSizePrefs
import com.example.weatherapp.presentation.setting_screen.model.Language

interface UserSettings {
    suspend fun setLanguage(language: Language)
    suspend fun getLanguage(): Language
    suspend fun setFontSizePrefs(fontSizePrefs: FontSizePrefs)
    suspend fun getFontSizePrefs(): FontSizePrefs
}