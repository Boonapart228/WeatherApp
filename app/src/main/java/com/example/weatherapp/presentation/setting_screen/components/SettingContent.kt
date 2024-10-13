package com.example.weatherapp.presentation.setting_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.presentation.bottom_bar.BottomBar
import com.example.weatherapp.presentation.navigation.model.Screens


@Composable
fun SettingContent(onBottomBarNavigationClick: (Screens) -> Unit, state: SettingState) {
    Scaffold(bottomBar = {
        BottomBar(
            onClick = onBottomBarNavigationClick,
            selected = state.selectedScreen
        )
    }) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(text = "Setting_Screen")
        }
    }

}

@Preview
@Composable
fun SettingContentPreview() {
}
