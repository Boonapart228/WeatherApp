package com.example.weatherapp.presentation.setting_screen.components

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.LocaleListCompat
import com.example.weatherapp.R
import com.example.weatherapp.presentation.bottom_bar.BottomBar
import com.example.weatherapp.presentation.navigation.model.Screens
import com.example.weatherapp.presentation.setting_screen.model.Languages


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingContent(onBottomBarNavigationClick: (Screens) -> Unit, state: SettingState) {

//    Scaffold(bottomBar = {
//        BottomBar(
//            onClick = onBottomBarNavigationClick,
//            selected = state.selectedScreen
//        )
//    }) {
        val localeOptions = mapOf(
            R.string.en to "en",
            R.string.uk to "uk",
        ).mapKeys { stringResource(it.key) }

        var expanded by remember { mutableStateOf(false) }

        var selectedLocale by remember { mutableStateOf(localeOptions.keys.first()) } // Вибрана мова

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
//                .padding(it)
        ) {
            Text(text = stringResource(id = R.string.time))
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded })
            {
                TextField(
                    readOnly = true,
                    value = selectedLocale,
                    onValueChange = {},
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    localeOptions.keys.forEach { selectionLocale ->
                        DropdownMenuItem(
                            text = { Text(text = selectionLocale) },
                            onClick = {
                                expanded = false
                                selectedLocale = selectionLocale
                                AppCompatDelegate.setApplicationLocales(
                                    LocaleListCompat.forLanguageTags(
                                        localeOptions[selectionLocale]
                                    )
                                )
                            })
                    }
                }

            }
        }
//    }

}

@Preview
@Composable
fun SettingContentPreview() {
    SettingContent({}, SettingState())
}
