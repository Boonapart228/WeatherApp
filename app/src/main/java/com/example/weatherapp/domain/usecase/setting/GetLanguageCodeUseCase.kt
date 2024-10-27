package com.example.weatherapp.domain.usecase.setting

import com.example.weatherapp.domain.repository.UserSettings
import kotlinx.coroutines.flow.Flow

class GetLanguageCodeUseCase(
    private val userSettings: UserSettings
) {
    fun execute(): Flow<String> {
        return userSettings.getLanguageCode()
    }
}