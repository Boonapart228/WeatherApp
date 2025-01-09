package com.example.weatherapp.data

import com.example.weatherapp.R
import com.example.weatherapp.domain.repository.EpochDateHandler
import java.util.Calendar
import java.util.Date

class EpochDateHandlerImpl : EpochDateHandler {
    override fun getShortDayName(dataEpoch: Int): Int {

        val days = mapOf(
            Calendar.MONDAY to R.string.monday_short,
            Calendar.TUESDAY to R.string.tuesday_short,
            Calendar.WEDNESDAY to R.string.wednesday_short,
            Calendar.THURSDAY to R.string.thursday_short,
            Calendar.FRIDAY to R.string.friday_short,
            Calendar.SATURDAY to R.string.saturday_short,
            Calendar.SUNDAY to R.string.sunday_short
        )

        val date = Date(dataEpoch.toLong() * 1000)
        val calendar = Calendar.getInstance()
        calendar.time = date

        return days[calendar.get(Calendar.DAY_OF_WEEK)] ?: R.string.unknown
    }
}