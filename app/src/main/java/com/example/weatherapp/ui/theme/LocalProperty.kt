package com.example.weatherapp.ui.theme

import androidx.compose.runtime.compositionLocalOf


val LocalProperty = compositionLocalOf {
    Property()
}

data class Property(
    val columnGridCells: Int = 2
)