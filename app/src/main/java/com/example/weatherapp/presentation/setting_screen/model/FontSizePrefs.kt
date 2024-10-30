package com.example.weatherapp.presentation.setting_screen.model

import com.example.weatherapp.R

enum class FontSizePrefs(
    val key: Int,
    val fontSizeExtra: Int
) {
    SMALL(R.string.font_size_s, -2),
    DEFAULT(R.string.font_size_m, 0),
    LARGE(R.string.font_size_l, 2);
    companion object {
        fun fromString(name: String): FontSizePrefs {
            return entries.find { it.name == name } ?: DEFAULT
        }
    }
}