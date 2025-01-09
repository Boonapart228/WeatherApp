package com.example.weatherapp.presentation.home_screen.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.LocalDimen
import com.example.weatherapp.ui.theme.LocalProperty

@Composable
fun WeatherForecast(
    icon: String,
    maxTemp: String,
    minTemp: String,
    avgTemp: String,
    shortDayName: Int

) {
    Card(
        modifier = Modifier
            .width(LocalDimen.current.cardWidth)
            .padding(LocalDimen.current.cardPadding),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalDimen.current.rowCardPadding)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(LocalDimen.current.columnVerticalArrangementSmallSpaced),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(LocalProperty.current.fortyPercent)
            ) {
                Text(
                    stringResource(id = shortDayName),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
                AsyncImage(
                    model = "https:$icon",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(LocalDimen.current.weatherIconForecastSize)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(LocalDimen.current.columnVerticalArrangementMediumSpaced),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(LocalProperty.current.sixtyPercent)
            ) {
                WeatherForecastInfo(R.string.max_short,maxTemp)
                WeatherForecastInfo(R.string.min_short,minTemp)
                WeatherForecastInfo( R.string.avg_short,avgTemp)
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeatherForecastPreview() {
    WeatherForecast("qwe", "20", "5", "14", 0)
}