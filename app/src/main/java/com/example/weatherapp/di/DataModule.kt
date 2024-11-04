package com.example.weatherapp.di

import android.app.Application
import android.content.Context
import com.example.weatherapp.data.LocationTrackerImpl
import com.example.weatherapp.data.UserSettingsImpl
import com.example.weatherapp.data.WeatherApiRepositoryImpl
import com.example.weatherapp.domain.repository.LocationTracker
import com.example.weatherapp.domain.repository.UserSettings
import com.example.weatherapp.domain.repository.WeatherApiRepository
import com.example.weatherapp.domain.usecase.setting.GetFontSizePrefsUseCase
import com.example.weatherapp.domain.usecase.setting.GetLanguageUseCase
import com.example.weatherapp.domain.usecase.setting.SetFontSizePrefsUseCase
import com.example.weatherapp.domain.usecase.weather.GetDataByQueryUseCase
import com.example.weatherapp.domain.usecase.setting.SetLanguageUseCase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideWeatherApiRepository(): WeatherApiRepository {
        return WeatherApiRepositoryImpl()
    }

    @Provides
    fun provideGetDataByQueryUseCase(weatherApiRepository: WeatherApiRepository): GetDataByQueryUseCase {
        return GetDataByQueryUseCase(weatherApiRepository)
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
    fun provideSetFontSizePrefsUseCase(userSettings: UserSettings): SetFontSizePrefsUseCase {
        return SetFontSizePrefsUseCase(userSettings)
    }

    @Provides
    fun provideGetFontSizePrefsUseCase(userSettings: UserSettings): GetFontSizePrefsUseCase {
        return GetFontSizePrefsUseCase(userSettings)
    }

    @Provides
    fun provideGetLanguageUseCase(userSettings: UserSettings): GetLanguageUseCase {
        return GetLanguageUseCase(userSettings)
    }

    @Provides
    @Singleton
    fun providesFusedLocationProviderClient(
        application: Application
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(application)
    }

    @Provides
    @Singleton
    fun providesLocationTracker(
        fusedLocationProviderClient: FusedLocationProviderClient,
        application: Application
    ): LocationTracker {
        return  LocationTrackerImpl(
            fusedLocationProviderClient = fusedLocationProviderClient,
            application = application
        )
    }
}