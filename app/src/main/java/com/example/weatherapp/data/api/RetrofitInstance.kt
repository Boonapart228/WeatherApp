package com.example.weatherapp.data.api

import com.example.weatherapp.domain.repository.WeatherApiRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.weatherapi.com"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val weatherApiRepository : WeatherApiRepository = getInstance().create(WeatherApiRepository::class.java)

}