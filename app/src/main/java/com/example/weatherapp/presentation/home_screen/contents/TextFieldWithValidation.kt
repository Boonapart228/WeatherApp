package com.example.weatherapp.presentation.home_screen.contents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.LocalProperty


@Composable
fun TextFieldWithValidation(
    value: String,
    isError: Boolean,
    supportingText: Int,
    onFindCityClick: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value, onValueChange = onValueChange,
        trailingIcon = {
            IconButton(onClick = { onValueChange("") }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null
                )
            }
        },
        leadingIcon = {
            AnimatedVisibility(
                visible = value.isNotBlank(),
                enter = slideInHorizontally(
                    animationSpec = tween(
                        LocalProperty.current.animationDurationMillis,
                        easing = FastOutSlowInEasing
                    ),
                    initialOffsetX = { fullWidth -> -fullWidth }
                ),
                exit = slideOutHorizontally(
                    animationSpec = tween(
                        LocalProperty.current.animationDurationMillis,
                        easing = FastOutLinearInEasing
                    ),
                    targetOffsetX = { fullWidth -> -fullWidth }
                )
            ) {
                IconButton(onClick = { onFindCityClick() }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            }
        },
        label = { Text(text = stringResource(id = R.string.enter_city_prompt)) },
        supportingText = { Text(text = stringResource(id = supportingText)) },
        singleLine = true,
        isError = isError,
        modifier = Modifier.fillMaxWidth()
    )
}
