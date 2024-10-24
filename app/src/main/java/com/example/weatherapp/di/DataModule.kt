package com.example.weatherapp.di

import com.example.weatherapp.data.WeatherApiRepositoryImpl
import com.example.weatherapp.domain.repository.WeatherApiRepository
import com.example.weatherapp.domain.usecase.GetDataByCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}