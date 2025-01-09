package com.example.weatherapp.domain.repository

interface EpochDateHandler {
    fun getShortDayName(dataEpoch : Int) : Int
}