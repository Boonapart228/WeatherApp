package com.example.weatherapp.domain.repository


import com.example.weatherapp.data.api.Constant
import com.example.weatherapp.domain.models.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiRepository {
    @GET("/v1/current.json")
    suspend fun getDataByQuery(
        @Query("key") apikey: String = Constant.API_KEY,
        @Query("q") query: String,
        @Query("lang") languageCode: String
    ): Response<WeatherModel>
}