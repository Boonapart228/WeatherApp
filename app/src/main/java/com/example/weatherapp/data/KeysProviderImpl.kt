package com.example.weatherapp.data

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.domain.repository.KeysProvider

class KeysProviderImpl : KeysProvider {
    override fun getWeatherApiKey(): String {
        return BuildConfig.API_KEY
    }
}