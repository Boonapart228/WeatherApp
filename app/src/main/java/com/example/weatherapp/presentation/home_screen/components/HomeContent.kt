package com.example.weatherapp.presentation.home_screen.components

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
fun HomeContent(
    onTopBarNavigationClick: (Screens) -> Unit,
    state: HomeState
) {
    Scaffold(bottomBar = {
        BottomBar(
            onClick = onTopBarNavigationClick,
            selected = state.selectedScreen
        )
    }) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(text = "Home_Screen")
        }
    }
}

@Preview
@Composable
fun HomeContentPreview() {
    HomeContent(onTopBarNavigationClick = {}, HomeState())
}