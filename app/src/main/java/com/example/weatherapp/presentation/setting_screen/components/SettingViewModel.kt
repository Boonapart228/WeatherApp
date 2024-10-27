package com.example.weatherapp.presentation.setting_screen.components

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecase.setting.GetLanguageIdUseCase
import com.example.weatherapp.domain.usecase.setting.SetLanguageUseCase
import com.example.weatherapp.presentation.navigation.model.Screens
import com.example.weatherapp.presentation.setting_screen.model.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val setLanguageUseCase: Provider<SetLanguageUseCase>,
    private val getLanguageIdUseCase: Provider<GetLanguageIdUseCase>
) : ViewModel() {

    private val _state = MutableStateFlow(SettingState())

    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SettingNavigationEvent>()

    val event = _event.asSharedFlow()

    init {
        getLanguageId()
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

    private fun getLanguageId() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedLocaleId = getLanguageIdUseCase.get().execute().first()
                )
            }
        }
    }

    fun setLanguage(selectionLocale: Language) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedLocaleId = selectionLocale.languageId
                )
            }
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(
                    selectionLocale.languageCode
                )
            )
            setLanguageUseCase.get().execute(selectionLocale)
        }
    }
}