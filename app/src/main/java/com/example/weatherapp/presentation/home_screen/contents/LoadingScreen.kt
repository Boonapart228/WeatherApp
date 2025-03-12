package com.example.weatherapp.presentation.home_screen.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.weatherapp.presentation.home_screen.model.WeatherDetail
import com.example.weatherapp.ui.theme.LocalDimen
import com.example.weatherapp.ui.theme.LocalProperty

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(LocalDimen.current.columnSpacing, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(LocalDimen.current.rowSpacing, Alignment.CenterHorizontally)
            ) {
                ShimmerEffect(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(LocalDimen.current.shimmerEffectMediumSize)
                )
                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(LocalDimen.current.shimmerEffectShortSize)
                )
            }
            ShimmerEffect(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(LocalDimen.current.shimmerEffectShortSize)
            )

            ShimmerEffect(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(LocalDimen.current.shimmerEffectLongSize)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(LocalProperty.current.columnGridCells),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(LocalDimen.current.lazyContentPadding),
                horizontalArrangement = Arrangement.spacedBy(LocalDimen.current.lazyHorizontalSpacing),
                verticalArrangement = Arrangement.spacedBy(LocalDimen.current.lazyVerticalSpacing)
            ) {
                WeatherDetail.entries.forEach { _ ->
                    item {
                        ShimmerEffect(Modifier.size(width = LocalDimen.current.shimmerEffectShortSize, height = LocalDimen.current.shimmerEffectMediumSize))
                    }
                }
            }
        }
    }
}