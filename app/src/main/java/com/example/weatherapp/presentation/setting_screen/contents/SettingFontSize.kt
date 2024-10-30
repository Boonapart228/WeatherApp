package com.example.weatherapp.presentation.setting_screen.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.weatherapp.R
import com.example.weatherapp.presentation.setting_screen.components.SettingState
import com.example.weatherapp.presentation.setting_screen.model.FontSizePrefs
import com.example.weatherapp.ui.theme.LocalDimen
import kotlin.math.roundToInt

@Composable
fun SettingFontSize(
    state: SettingState,
    setFontSize: (FontSizePrefs) -> Unit
) {
    val fontSizeOptions = FontSizePrefs.entries.toTypedArray()


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalDimen.current.rowPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_text_format_24),
            contentDescription = null,
            modifier = Modifier.size(LocalDimen.current.iconSize),
        )

        Spacer(modifier = Modifier.width(LocalDimen.current.spacerWidth))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = LocalDimen.current.columnVerticalPadding)
        ) {
            Text(
                text = stringResource(id = R.string.select_font_size),
                fontWeight = FontWeight.Medium,
            )

            Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight))

            Slider(
                value = state.currentFontSizeIndex.toFloat(),
                onValueChange = { newPosition ->
                    val index = newPosition.roundToInt().coerceIn(0, fontSizeOptions.size - 1)
                    setFontSize(fontSizeOptions[index])
                },
                valueRange = 0f..(fontSizeOptions.size - 1).toFloat(),
                steps = fontSizeOptions.size - 2,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight))

            Text(
                text = stringResource(id = state.selectedFontSize),
            )
        }
    }

}