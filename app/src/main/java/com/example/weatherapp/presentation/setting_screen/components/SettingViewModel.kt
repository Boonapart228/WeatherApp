package com.example.weatherapp.presentation.setting_screen.components

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecase.setting.GetFontSizePrefsUseCase
import com.example.weatherapp.domain.usecase.setting.GetLanguageIdUseCase
import com.example.weatherapp.domain.usecase.setting.SetFontSizePrefsUseCase
import com.example.weatherapp.domain.usecase.setting.SetLanguageUseCase
import com.example.weatherapp.presentation.navigation.model.Screens
import com.example.weatherapp.presentation.setting_screen.model.FontSizePrefs
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
    private val getLanguageIdUseCase: Provider<GetLanguageIdUseCase>,
    private val setFontSizePrefsUseCase: Provider<SetFontSizePrefsUseCase>,
    private val getFontSizePrefsUseCase: Provider<GetFontSizePrefsUseCase>
) : ViewModel() {

    private val _state = MutableStateFlow(SettingState())

    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SettingNavigationEvent>()

    val event = _event.asSharedFlow()

    companion object {
        const val LINE_HEIGHT_MULTIPLIER = 1.15
    }

    init {
        getLanguageId()
        getFontSizePrefs()
    }

    fun onBottomBarNavigationClick(route: Screens) {
        when (route) {
            Screens.SETTING_SCREEN -> {}
            Screens.HOME_SCREEN -> viewModelScope.launch { _event.emit(SettingNavigationEvent.NavigationToHome) }
        }
    }

    fun onToggleLanguageMenu() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    expandedLanguageMenu = !it.expandedLanguageMenu
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

    private fun getFontSizePrefs() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedFontSize = getFontSizePrefsUseCase.get().execute().key,
                    fontSizePrefs = getFontSizePrefsUseCase.get().execute(),
                    currentFontSizeIndex = FontSizePrefs.entries.indexOf(
                        getFontSizePrefsUseCase.get().execute()
                    )
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

    fun setFontSize(fontSizePrefs: FontSizePrefs) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    fontSizePrefs = fontSizePrefs,
                    selectedFontSize = fontSizePrefs.key,
                    currentFontSizeIndex = FontSizePrefs.entries.indexOf(fontSizePrefs)
                )
            }
            setFontSizePrefsUseCase.get().execute(fontSizePrefs)
        }
    }

    fun getPersonalizedTypography(fontSizePrefs: FontSizePrefs): Typography {
        return Typography(
            bodyLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = (16 + fontSizePrefs.fontSizeExtra).sp,
                lineHeight = ((16 + fontSizePrefs.fontSizeExtra) * LINE_HEIGHT_MULTIPLIER).sp,
                letterSpacing = 0.5.sp
            ),
            titleLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = (22 + fontSizePrefs.fontSizeExtra).sp,
                lineHeight = ((22 + fontSizePrefs.fontSizeExtra) * LINE_HEIGHT_MULTIPLIER).sp,
                letterSpacing = 0.sp
            ),
            titleMedium = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.SemiBold,
                fontSize = (18 + fontSizePrefs.fontSizeExtra).sp,
                lineHeight = ((18 + fontSizePrefs.fontSizeExtra) * LINE_HEIGHT_MULTIPLIER).sp,
                letterSpacing = 0.sp
            ),
            labelSmall = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium,
                fontSize = (11 + fontSizePrefs.fontSizeExtra).sp,
                lineHeight = ((11 + fontSizePrefs.fontSizeExtra) * LINE_HEIGHT_MULTIPLIER).sp,
                letterSpacing = 0.5.sp
            ),
        )
    }
}


