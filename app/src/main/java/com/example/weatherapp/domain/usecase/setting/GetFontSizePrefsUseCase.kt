package com.example.weatherapp.domain.usecase.setting

import com.example.weatherapp.domain.repository.UserSettings
import com.example.weatherapp.presentation.setting_screen.model.FontSizePrefs

class GetFontSizePrefsUseCase(
    private val userSettings: UserSettings
) {
    suspend fun execute(): FontSizePrefs {
        return userSettings.getFontSizePrefs()
    }
}