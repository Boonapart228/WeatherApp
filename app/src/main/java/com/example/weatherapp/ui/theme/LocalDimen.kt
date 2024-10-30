package com.example.weatherapp.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val LocalDimen = compositionLocalOf {
    Dimensions()
}

data class Dimensions(
    val iconSize: Dp = 36.dp,
    val largeIconSize: Dp = 160.dp,
    val shimmerEffectShape: Dp = 12.dp,
    val columnWidth: Dp = 16.dp,
    val columnVerticalPadding: Dp = 8.dp,
    val shimmerEffectShortSize: Dp = 12.dp,
    val shimmerEffectMediumSize: Dp = 32.dp,
    val shimmerEffectLongSize: Dp = 120.dp,
    val columnSpacing: Dp = 24.dp,
    val rowSpacing: Dp = 12.dp,
    val rowDetailsSpacing: Dp = 2.dp,
    val lazyContentPadding: Dp = 8.dp,
    val lazyHorizontalSpacing: Dp = 8.dp,
    val lazyVerticalSpacing: Dp = 8.dp,
    val columnPadding: Dp = 16.dp,
    val columnDetailsSpacing: Dp = 8.dp,
    val largeTextSize: TextUnit = 32.sp,
    val mediumTextSize: TextUnit = 24.sp,
    val rowPadding: Dp = 16.dp,
    val spacerWidth: Dp = 16.dp,
    val spacerHeight: Dp = 8.dp
)