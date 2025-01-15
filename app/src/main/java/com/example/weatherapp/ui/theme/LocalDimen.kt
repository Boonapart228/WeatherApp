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
    val weatherIconForecastSize: Dp = 48.dp,
    val largeIconSize: Dp = 120.dp,
    val shimmerEffectShape: Dp = 12.dp,
    val columnWidth: Dp = 16.dp,
    val columnVerticalPadding: Dp = 8.dp,
    val columnVerticalArrangementSmallSpaced: Dp = 2.dp,
    val columnVerticalArrangementMediumSpaced: Dp = 8.dp,
    val shimmerEffectShortSize: Dp = 12.dp,
    val shimmerEffectMediumSize: Dp = 32.dp,
    val shimmerEffectLongSize: Dp = 120.dp,
    val columnSpacing: Dp = 4.dp,
    val rowSpacing: Dp = 12.dp,
    val rowDetailsSpacing: Dp = 2.dp,
    val lazyContentPadding: Dp = 8.dp,
    val lazyRowContentPadding: Dp = 4.dp,
    val lazyRowMinHeight: Dp = 100.dp,
    val lazyRowMaxHeight: Dp = 120.dp,
    val lazyHorizontalSpacing: Dp = 8.dp,
    val lazyVerticalSpacing: Dp = 8.dp,
    val columnPadding: Dp = 16.dp,
    val columnDetailsSpacing: Dp = 8.dp,
    val largeTextSize: TextUnit = 32.sp,
    val mediumTextSize: TextUnit = 24.sp,
    val rowPadding: Dp = 16.dp,
    val rowCardPadding: Dp = 4.dp,
    val cardPadding: Dp = 4.dp,
    val cardWidth: Dp = 180.dp,
    val spacerWidth: Dp = 16.dp,
    val spacerHeight: Dp = 8.dp,
    val rowVerticalPadding : Dp = 8.dp,
    val outlinedButtonPaddingEnd : Dp = 8.dp,
    val iconPaddingStart : Dp = 4.dp,
    val spacerPaddingVertical : Dp = 4.dp
)