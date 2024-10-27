package com.example.weatherapp.presentation.setting_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.presentation.bottom_bar.BottomBar
import com.example.weatherapp.presentation.navigation.model.Screens
import com.example.weatherapp.presentation.setting_screen.model.Language


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingContent(
    state: SettingState,
    onHandleExpanded: () -> Unit,
    setLanguage: (Language) -> Unit,
    onBottomBarNavigationClick: (Screens) -> Unit
) {

    Scaffold(bottomBar = {
        BottomBar(
            onClick = onBottomBarNavigationClick,
            selected = state.selectedScreen
        )
    }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(text = stringResource(id = R.string.select_language))
            ExposedDropdownMenuBox(
                expanded = state.expanded,
                onExpandedChange = { onHandleExpanded() }
            )
            {
                TextField(
                    readOnly = true,
                    value = stringResource(id = state.selectedLocaleId),

                    onValueChange = {},
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = state.expanded
                        )
                    },
                    modifier = Modifier.menuAnchor(
                        type = MenuAnchorType.PrimaryEditable,
                        enabled = true
                    )
                )
                ExposedDropdownMenu(
                    expanded = state.expanded,
                    onDismissRequest = { onHandleExpanded() }) {
                    Language.entries.forEach { element ->
                        DropdownMenuItem(
                            text = { Text(stringResource(id = element.languageId)) },
                            onClick = {
                                onHandleExpanded()
                                setLanguage(element)
                            })
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingContentPreview() {
    SettingContent(SettingState(), {}, {}, {})
}
