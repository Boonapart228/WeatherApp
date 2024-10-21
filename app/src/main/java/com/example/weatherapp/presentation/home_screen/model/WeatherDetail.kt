package com.example.weatherapp.presentation.home_screen.model

import com.example.weatherapp.R
import com.example.weatherapp.domain.models.WeatherModel

enum class WeatherDetail(
    val labelResId: Int,
    val valueExtractor: (WeatherModel) -> String,
    val symbolId: Int
) {
    TEMPERATURE(R.string.temperature, { it.current.temp_c.toString() }, R.string.c),
    WIND(R.string.wind, { it.current.wind_kph.toString() }, R.string.kilometer_per_hour),
    FEELS_LIKE(R.string.feelsLike, { it.current.feelslike_c.toString() }, R.string.c),
    HUMIDITY(R.string.humidity, { it.current.humidity.toString() }, R.string.percent),
    TIME(R.string.time, { it.location.localtime.drop(11) }, R.string.empty),
    LAST_UPDATED(R.string.data_last_update, { it.current.last_updated }, R.string.empty)
}