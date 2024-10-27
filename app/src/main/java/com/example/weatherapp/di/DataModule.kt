package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.data.UserSettingsImpl
import com.example.weatherapp.data.WeatherApiRepositoryImpl
import com.example.weatherapp.domain.repository.UserSettings
import com.example.weatherapp.domain.repository.WeatherApiRepository
import com.example.weatherapp.domain.usecase.setting.GetLanguageCodeUseCase
import com.example.weatherapp.domain.usecase.setting.GetLanguageIdUseCase
import com.example.weatherapp.domain.usecase.weather.GetDataByCityUseCase
import com.example.weatherapp.domain.usecase.setting.SetLanguageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideWeatherApiRepository(): WeatherApiRepository {
        return WeatherApiRepositoryImpl()
    }

    @Provides
    fun provideGetDataByCityUseCase(weatherApiRepository: WeatherApiRepository): GetDataByCityUseCase {
        return GetDataByCityUseCase(weatherApiRepository)
    }

    @Provides
    fun provideUserSettings(@ApplicationContext applicationContext: Context): UserSettings {
        return UserSettingsImpl(applicationContext)
    }

    @Provides
    fun provideSetLanguageUseCase(userSettings: UserSettings): SetLanguageUseCase {
        return SetLanguageUseCase(userSettings)
    }

    @Provides
    fun provideGetLanguageCodeUseCase(userSettings: UserSettings): GetLanguageCodeUseCase {
        return GetLanguageCodeUseCase(userSettings)
    }

    @Provides
    fun provideGetLanguageIdUseCase(userSettings: UserSettings): GetLanguageIdUseCase {
        return GetLanguageIdUseCase(userSettings)
    }
}