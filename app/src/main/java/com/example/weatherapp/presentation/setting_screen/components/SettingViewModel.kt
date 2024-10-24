package com.example.weatherapp.presentation.setting_screen.components

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.presentation.navigation.model.Screens
import com.example.weatherapp.presentation.setting_screen.model.Languages
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingViewModel @Inject constructor(
) : ViewModel() {

    private val _state = MutableStateFlow(SettingState())

    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SettingNavigationEvent>()

    val event = _event.asSharedFlow()

    init {
        getLanguage()
    }


    fun onBottomBarNavigationClick(route: Screens) {
        when (route) {
            Screens.SETTING_SCREEN -> {}
            Screens.HOME_SCREEN -> viewModelScope.launch { _event.emit(SettingNavigationEvent.NavigationToHome) }
        }
    }

    fun onHandleExpanded() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    expanded = !it.expanded
                )
            }
        }
    }

    private fun getLanguage() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedLocale = Languages.entries.first().languageId
                )
            }
        }
    }

    fun setLanguage(selectionLocale: Languages) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedLocale = selectionLocale.languageId
                )
            }
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(
                    selectionLocale.languageCode
                )
            )
        }
    }


}