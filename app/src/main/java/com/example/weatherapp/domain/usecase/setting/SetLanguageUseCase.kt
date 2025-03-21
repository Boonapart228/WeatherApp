package com.example.weatherapp.domain.usecase.setting

import com.example.weatherapp.domain.repository.UserSettings
import com.example.weatherapp.presentation.setting_screen.model.Language

class SetLanguageUseCase(
    private val userSettings: UserSettings
) {
    suspend fun execute(language: Language) {
        userSettings.setLanguage(language)
    }
}