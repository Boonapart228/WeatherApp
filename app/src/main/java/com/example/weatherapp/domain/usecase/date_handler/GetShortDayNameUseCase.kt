package com.example.weatherapp.domain.usecase.date_handler

import com.example.weatherapp.domain.repository.EpochDateHandler

class GetShortDayNameUseCase(
    private val epochDateHandler: EpochDateHandler
) {
    fun execute(dataEpoch : Int) : Int{
        return epochDateHandler.getShortDayName(dataEpoch)
    }
}