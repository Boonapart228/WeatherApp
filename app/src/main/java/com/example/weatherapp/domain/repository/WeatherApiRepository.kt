package com.example.weatherapp.domain.repository


import com.example.weatherapp.domain.models.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiRepository {
    @GET("/v1/forecast.json")
    suspend fun getDataByQuery(
        @Query("key") apikey: String,
        @Query("q") query: String,
        @Query("lang") languageCode: String,
        @Query("days") days : Int = 3
    ): Response<WeatherModel>
}