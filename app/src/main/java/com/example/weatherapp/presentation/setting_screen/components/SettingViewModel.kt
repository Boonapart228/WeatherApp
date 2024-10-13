package com.example.weatherapp.presentation.setting_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.presentation.navigation.model.Screens
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingViewModel : ViewModel() {

    private val _state = MutableStateFlow(SettingState())

    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SettingNavigationEvent>()

    val event = _event.asSharedFlow()

    fun onTopBarNavigationClick(route: Screens) {
        when (route) {
            Screens.SETTING_SCREEN -> {}
            Screens.HOME_SCREEN -> viewModelScope.launch { _event.emit(SettingNavigationEvent.NavigationToHome) }
        }
    }
}