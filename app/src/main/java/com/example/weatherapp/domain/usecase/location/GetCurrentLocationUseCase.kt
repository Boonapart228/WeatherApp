package com.example.weatherapp.domain.usecase.location

import android.location.Location
import com.example.weatherapp.domain.repository.LocationTracker

class GetCurrentLocationUseCase(
    private val locationTracker: LocationTracker
) {
    suspend fun execute() : Location?{
        return locationTracker.getCurrentLocation()
    }
}