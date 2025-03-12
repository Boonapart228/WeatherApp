package com.example.weatherapp.presentation.setting_screen.model

import com.example.weatherapp.R

enum class Language(
    val languageId: Int,
    val languageCode : String
) {
    ENGLISH(R.string.en, "en"),
    UKRAINE(R.string.uk, "uk");
    companion object {
        fun fromString(name: String): Language {
            return entries.find { it.name == name } ?: ENGLISH
        }
    }
}