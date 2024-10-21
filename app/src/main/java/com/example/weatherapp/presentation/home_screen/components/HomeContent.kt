package com.example.weatherapp.presentation.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.domain.models.NetworkResponse
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.presentation.bottom_bar.BottomBar
import com.example.weatherapp.presentation.home_screen.contents.ErrorScreen
import com.example.weatherapp.presentation.home_screen.contents.LoadingScreen
import com.example.weatherapp.presentation.home_screen.contents.TextFieldWithValidation
import com.example.weatherapp.presentation.home_screen.contents.WeatherItem
import com.example.weatherapp.presentation.navigation.model.Screens
import com.example.weatherapp.ui.theme.LocalDimen

@Composable
fun HomeContent(
    onBottomBarNavigationClick: (Screens) -> Unit,
    setCity: (String) -> Unit,
    onFindCityClick: () -> Unit,
    weatherResult: NetworkResponse<WeatherModel>,
    state: HomeState,
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(bottomBar = {
        BottomBar(
            onClick = onBottomBarNavigationClick,
            selected = state.selectedScreen
        )
    }) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(LocalDimen.current.columnWidth)
        ) {
            TextFieldWithValidation(
                value = state.city,
                onValueChange = setCity,
                isError = state.inValidCity,
                supportingText = state.supportingText,
                onFindCityClick = {
                    onFindCityClick()
                    keyboardController?.hide()
                }
            )

            when (weatherResult) {
                is NetworkResponse.Error -> ErrorScreen(messageId = weatherResult.message)
                NetworkResponse.Loading -> LoadingScreen()
                is NetworkResponse.Success -> {
                    WeatherItem(
                        weatherResult.data
                    )
                }

                NetworkResponse.Default -> {}
            }
        }
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