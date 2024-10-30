package com.example.weatherapp.presentation.setting_screen.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.presentation.setting_screen.components.SettingState
import com.example.weatherapp.presentation.setting_screen.model.Language

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingLanguage(
    state: SettingState,
    onToggleLanguageMenu: () -> Unit,
    setLanguage: (Language) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.select_language), fontWeight = FontWeight.Medium)
        ExposedDropdownMenuBox(
            expanded = state.expandedLanguageMenu,
            onExpandedChange = { onToggleLanguageMenu() }
        )
        {
            TextField(
                readOnly = true,
                value = stringResource(id = state.selectedLocaleId),
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .menuAnchor(
                        type = MenuAnchorType.PrimaryEditable,
                        enabled = true
                    ),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center
                )
            )
            ExposedDropdownMenu(
                expanded = state.expandedLanguageMenu,
                onDismissRequest = { onToggleLanguageMenu() }) {
                Language.entries.forEach { element ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                stringResource(id = element.languageId),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        },
                        onClick = {
                            onToggleLanguageMenu()
                            setLanguage(element)
                        })
                    if (element != Language.entries.last()) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SettingLanguagePreview() {
    SettingLanguage(state = SettingState(), onToggleLanguageMenu = { }, setLanguage = {})
}