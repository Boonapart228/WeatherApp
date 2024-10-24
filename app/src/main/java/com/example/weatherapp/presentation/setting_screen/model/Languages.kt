package com.example.weatherapp.presentation.setting_screen.model

import com.example.weatherapp.R

enum class Languages(
    val languageId: Int,
    val languageCode : String
) {
    ENGLISH(R.string.en, "en"),
    UKRAINE(R.string.uk, "uk")
}