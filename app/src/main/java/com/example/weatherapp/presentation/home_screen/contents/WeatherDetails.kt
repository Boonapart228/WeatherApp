package com.example.weatherapp.presentation.home_screen.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.weatherapp.ui.theme.LocalDimen
import com.example.weatherapp.ui.theme.LocalProperty

@Composable
fun WeatherDetails(
    titleId: Int,
    info: String,
    symbolId: Int,
    modifier: Modifier = Modifier,
    onToggleVisibility: () -> Unit = {},
    isTextFullyVisible: Boolean = false,
) {
    Card(
        modifier = modifier.fillMaxHeight(),
        onClick = {
            onToggleVisibility()
        }
    ) {
        Column(
            modifier = Modifier.padding(LocalDimen.current.columnPadding),
            verticalArrangement = Arrangement.spacedBy(LocalDimen.current.columnDetailsSpacing),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = titleId),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                overflow = if (isTextFullyVisible) TextOverflow.Ellipsis else TextOverflow.Visible,
                maxLines = if (isTextFullyVisible) LocalProperty.current.minLines else LocalProperty.current.maxLines
            )
            Row(horizontalArrangement = Arrangement.spacedBy(LocalDimen.current.rowDetailsSpacing)) {
                Text(
                    text = info
                )
                Text(
                    text = stringResource(id = symbolId)
                )
            }
        }
    }
}