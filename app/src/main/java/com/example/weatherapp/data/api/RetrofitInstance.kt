package com.example.weatherapp.data.api

import com.example.weatherapp.domain.repository.WeatherApiRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val baseUrl = "https://api.weatherapi.com"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val weatherApiRepository : WeatherApiRepository = getInstance().create(WeatherApiRepository::class.java)

}