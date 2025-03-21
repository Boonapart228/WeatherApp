package com.example.weatherapp.presentation.home_screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.weatherapp.R
import com.example.weatherapp.presentation.home_screen.components.HomeContent
import com.example.weatherapp.presentation.home_screen.components.HomeNavigationEvent
import com.example.weatherapp.presentation.home_screen.components.HomeViewModel
import com.example.weatherapp.presentation.home_screen.model.PermissionEvent
import com.example.weatherapp.presentation.home_screen.util.coarseAndFinePermissionsGranted
import com.example.weatherapp.presentation.home_screen.util.coarsePermissionGranted
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
    val context = LocalContext.current
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
                    if (!locationPermissions.allPermissionsGranted) {
                        Toast.makeText(
                            context,
                            R.string.geolocation_permission_message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }
    }

    HomeContent(
        state = state,
        onBottomBarNavigationClick = viewModel::onBottomBarNavigationClick,
        setCity = viewModel::setCity,
        onFindWeatherByCityClick = viewModel::onFindWeatherByCityClick,
        weatherResult = weatherResult,
        onFindWeatherByLocationClick = {
            when {
                locationPermissions.coarseAndFinePermissionsGranted() -> {
                    viewModel.onFindWeatherByLocation()
                }

                locationPermissions.coarsePermissionGranted() -> {
                    viewModel.onFindWeatherByLocation()
                }

                else -> {
                    viewModel.onLocalePermissionClick(PermissionEvent.LocationPermissionEvent)
                }
            }
        },
        onToggleVisibility = viewModel::onToggleVisibility,
        onRefreshWeatherClick = viewModel::onRefreshWeatherClick,
        getShortDayName = viewModel::getShortDayName
    )
}