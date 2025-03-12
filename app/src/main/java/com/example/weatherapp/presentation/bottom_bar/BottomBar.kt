package com.example.weatherapp.presentation.bottom_bar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.presentation.bottom_bar.model.BottomBarItems
import com.example.weatherapp.presentation.navigation.model.Screens

@Composable
fun BottomBar(
    onClick: (Screens) -> Unit,
    selected: Screens
) {
    NavigationBar {
        BottomBarItems.entries.forEach {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = it.label)) },
                selected = it.route == selected,
                onClick = { onClick(it.route) }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomBarPreview() {
    BottomBar({}, Screens.SETTING_SCREEN)
}