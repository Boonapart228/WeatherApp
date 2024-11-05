package com.example.weatherapp.presentation.home_screen.util

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionStatus

// Перевірка, чи надані обидва дозволи (точний і приблизний)
@OptIn(ExperimentalPermissionsApi::class)
fun MultiplePermissionsState.coarseAndFinePermissionsGranted(): Boolean {
    val coarsePermission =
        permissions.find { it.permission == android.Manifest.permission.ACCESS_COARSE_LOCATION }
    val finePermission =
        permissions.find { it.permission == android.Manifest.permission.ACCESS_FINE_LOCATION }
    return coarsePermission?.status is PermissionStatus.Granted &&
            finePermission?.status is PermissionStatus.Granted
}

// Перевірка, чи наданий тільки `ACCESS_COARSE_LOCATION`
@OptIn(ExperimentalPermissionsApi::class)
fun MultiplePermissionsState.coarsePermissionGranted(): Boolean {
    val coarsePermission =
        permissions.find { it.permission == android.Manifest.permission.ACCESS_COARSE_LOCATION }
    return coarsePermission?.status is PermissionStatus.Granted
}