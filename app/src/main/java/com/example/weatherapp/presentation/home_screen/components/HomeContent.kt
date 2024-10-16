package com.example.weatherapp.presentation.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.domain.models.NetworkResponse
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.presentation.bottom_bar.BottomBar
import com.example.weatherapp.presentation.navigation.model.Screens

@Composable
fun HomeContent(
    onBottomBarNavigationClick: (Screens) -> Unit,
    setCity: (String) -> Unit,
    onFindCityClick: () -> Unit,
    weatherResult: NetworkResponse<WeatherModel>,
    state: HomeState
) {
    Scaffold(bottomBar = {
        BottomBar(
            onClick = onBottomBarNavigationClick,
            selected = state.selectedScreen
        )
    }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            TextField(value = state.city, onValueChange = setCity)
            Button(onClick = { onFindCityClick() }) {
                Text("Find City")
            }

            when (weatherResult) {
                is NetworkResponse.Error -> Text(text = weatherResult.message)
                NetworkResponse.Loading -> CircularProgressIndicator()
                is NetworkResponse.Success -> WeatherItem(weatherResult.data)
                NetworkResponse.Default -> {}
            }
        }
    }
}


@Composable
fun WeatherItem(data: WeatherModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = data.current.temp_c.toString())
        Text(text = data.location.name)
    }

}


@Preview
@Composable
fun HomeContentPreview() {
    HomeContent(
        onBottomBarNavigationClick = {},
        {},
        {},
        weatherResult = NetworkResponse.Default,
        state = HomeState()
    )
}