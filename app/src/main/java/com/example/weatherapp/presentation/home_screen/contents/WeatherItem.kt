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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.presentation.home_screen.model.WeatherDetail


@Composable
fun WeatherItem(
    data: WeatherModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
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
                modifier = Modifier.size(32.dp)
            )
            Text(text = data.location.name, fontSize = 32.sp, fontWeight = FontWeight.Medium)
        }
        Text(text = data.current.condition.text, fontSize = 24.sp)
        AsyncImage(
            modifier = Modifier.size(160.dp),
            model = "https:${data.current.condition.icon.replace("64x64", "128x128")}",
            contentDescription = null,
            placeholder = painterResource(R.drawable.ic_launcher_foreground)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            WeatherDetail.entries.forEach { detail ->
                item {
                    WeatherDetails(detail.labelResId, detail.valueExtractor(data), detail.symbolId)
                }
            }
        }
    }
}