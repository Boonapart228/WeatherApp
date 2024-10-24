package com.example.weatherapp.presentation.setting_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.weatherapp.presentation.setting_screen.components.SettingContent
import com.example.weatherapp.presentation.setting_screen.components.SettingNavigationEvent
import com.example.weatherapp.presentation.setting_screen.components.SettingViewModel

@Composable
fun SettingScreen(
    viewModel: SettingViewModel,
    navigationToHome: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                SettingNavigationEvent.NavigationToHome -> navigationToHome()
            }
        }

    }
    SettingContent(
        onBottomBarNavigationClick = viewModel::onBottomBarNavigationClick,
        state = state,
        onHandleExpanded = viewModel::onHandleExpanded,
        setLanguage = viewModel::setLanguage
    )
}