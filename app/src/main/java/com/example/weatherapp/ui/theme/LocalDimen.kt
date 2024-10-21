package com.example.weatherapp.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalDimen = compositionLocalOf {
    Dimensions()
}

data class Dimensions(
    val iconSize: Dp = 24.dp,
    val shimmerEffectShape: Dp = 12.dp,
)