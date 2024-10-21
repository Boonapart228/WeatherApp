package com.example.weatherapp.presentation.home_screen.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.presentation.home_screen.model.WeatherDetail
import com.example.weatherapp.ui.theme.LocalDimen
import com.example.weatherapp.ui.theme.LocalProperty


@Composable
fun WeatherItem(
    data: WeatherModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(LocalDimen.current.columnSpacing, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(LocalDimen.current.iconSize)
            )
            Text(text = data.location.name, fontSize = LocalDimen.current.largeTextSize, fontWeight = FontWeight.Medium)
        }
        Text(text = data.current.condition.text, fontSize = LocalDimen.current.mediumTextSize)
        AsyncImage(
            modifier = Modifier.size(LocalDimen.current.largeIconSize),
            model = "https:${data.current.condition.icon.replace("64x64", "128x128")}",
            contentDescription = null,
            placeholder = painterResource(R.drawable.ic_launcher_foreground)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(LocalProperty.current.columnGridCells),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(LocalDimen.current.lazyContentPadding),
            horizontalArrangement = Arrangement.spacedBy(LocalDimen.current.lazyHorizontalSpacing),
            verticalArrangement = Arrangement.spacedBy(LocalDimen.current.lazyVerticalSpacing)
        ) {
            WeatherDetail.entries.forEach { detail ->
                item {
                    WeatherDetails(detail.labelResId, detail.valueExtractor(data), detail.symbolId)
                }
            }
        }
    }
}