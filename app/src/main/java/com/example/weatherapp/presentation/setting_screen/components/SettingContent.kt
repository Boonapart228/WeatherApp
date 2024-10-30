package com.example.weatherapp.presentation.setting_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.presentation.bottom_bar.BottomBar
import com.example.weatherapp.presentation.navigation.model.Screens
import com.example.weatherapp.presentation.setting_screen.contents.SettingFontSize
import com.example.weatherapp.presentation.setting_screen.contents.SettingLanguage
import com.example.weatherapp.presentation.setting_screen.model.FontSizePrefs
import com.example.weatherapp.presentation.setting_screen.model.Language


@Composable
fun SettingContent(
    state: SettingState,
    onToggleLanguageMenu: () -> Unit,
    setLanguage: (Language) -> Unit,
    setFontSize: (FontSizePrefs) -> Unit,
    onBottomBarNavigationClick: (Screens) -> Unit
) {
    Scaffold(bottomBar = {
        BottomBar(
            onClick = onBottomBarNavigationClick,
            selected = state.selectedScreen
        )
    }) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            SettingFontSize(
                state = state,
                setFontSize = setFontSize
            )
            HorizontalDivider()
            SettingLanguage(
                state = state,
                onToggleLanguageMenu = onToggleLanguageMenu,
                setLanguage = setLanguage
            )
        }
    }
}

@Preview
@Composable
fun SettingContentPreview() {
    SettingContent(SettingState(), {}, {}, {}, {})
}
