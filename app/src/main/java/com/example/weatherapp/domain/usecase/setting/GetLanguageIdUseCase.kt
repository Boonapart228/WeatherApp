package com.example.weatherapp.domain.usecase.setting

import com.example.weatherapp.domain.repository.UserSettings
import kotlinx.coroutines.flow.Flow

class GetLanguageIdUseCase(
    private val userSettings: UserSettings
) {
    fun execute(): Flow<Int> {
        return userSettings.getLanguageId()
    }
}