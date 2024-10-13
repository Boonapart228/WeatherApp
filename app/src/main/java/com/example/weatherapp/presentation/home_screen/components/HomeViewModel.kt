package com.example.weatherapp.presentation.home_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.presentation.navigation.model.Screens
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeState())

    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<HomeNavigationEvent>()

    val event = _event.asSharedFlow()

    fun onTopBarNavigationClick(route: Screens) {
        when (route) {
            Screens.SETTING_SCREEN -> viewModelScope.launch { _event.emit(HomeNavigationEvent.NavigationToSetting) }
            Screens.HOME_SCREEN -> {}
        }
    }
}