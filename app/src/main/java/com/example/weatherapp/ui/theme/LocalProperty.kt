package com.example.weatherapp.ui.theme

import androidx.compose.runtime.compositionLocalOf


val LocalProperty = compositionLocalOf {
    Property()
}

data class Property(
    val columnGridCells: Int = 2,
    val minLines: Int = 1,
    val maxLines: Int = 100,
    val animationDurationMillis : Int = 300,
    val fortyPercent : Float = 4f,
    val sixtyPercent : Float = 6f
)