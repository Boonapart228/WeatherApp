package com.example.weatherapp.presentation.home_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.weatherapp.presentation.home_screen.components.HomeContent
import com.example.weatherapp.presentation.home_screen.components.HomeNavigationEvent
import com.example.weatherapp.presentation.home_screen.components.HomeViewModel
import com.example.weatherapp.presentation.home_screen.model.PermissionEvent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigationToSetting: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val weatherResult by viewModel.weatherResult.collectAsState()
    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                HomeNavigationEvent.NavigationToSetting -> navigationToSetting()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.permissionEvent.collect {
            when (it) {
                PermissionEvent.LocationPermissionEvent -> {
                    locationPermissions.launchMultiplePermissionRequest()
                }
            }
        }
    }

    HomeContent(
        onBottomBarNavigationClick = viewModel::onBottomBarNavigationClick,
        setCity = viewModel::setCity,
        onFindWeatherByCityClick = viewModel::onFindWeatherByCityClick,
        weatherResult = weatherResult,
        onFindWeatherByLocationClick = {
            if (locationPermissions.allPermissionsGranted) {
                viewModel.onFindWeatherByLocation()
            } else {
                viewModel.onLocalePermissionClick(PermissionEvent.LocationPermissionEvent)
            }
        },
        state = state
    )
}