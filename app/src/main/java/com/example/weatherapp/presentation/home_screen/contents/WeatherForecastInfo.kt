package com.example.weatherapp.presentation.home_screen.contents

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun WeatherForecastInfo(textId : Int,value : String){
    Text(
        text = stringResource(id = textId) + ": $value",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge
    )
}