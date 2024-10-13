package com.example.weatherapp.presentation.home_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.weatherapp.presentation.home_screen.components.HomeContent
import com.example.weatherapp.presentation.home_screen.components.HomeNavigationEvent
import com.example.weatherapp.presentation.home_screen.components.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigationToSetting: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                HomeNavigationEvent.NavigationToSetting -> navigationToSetting()
            }
        }

    }
    HomeContent(
        onTopBarNavigationClick = viewModel::onTopBarNavigationClick,
        state = state
    )
}