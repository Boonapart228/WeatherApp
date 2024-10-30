package com.example.weatherapp.presentation.home_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
import com.example.weatherapp.domain.models.NetworkResponse
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.usecase.setting.GetLanguageUseCase
import com.example.weatherapp.domain.usecase.weather.GetDataByCityUseCase
import com.example.weatherapp.presentation.navigation.model.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDataByCityUseCase: Provider<GetDataByCityUseCase>,
    private val getLanguageUseCase: Provider<GetLanguageUseCase>
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())

    val state = _state.asStateFlow()


    private val _event = MutableSharedFlow<HomeNavigationEvent>()

    val event = _event.asSharedFlow()

    private val _weatherResult =
        MutableStateFlow<NetworkResponse<WeatherModel>>(NetworkResponse.Default)

    val weatherResult: StateFlow<NetworkResponse<WeatherModel>> = _weatherResult.asStateFlow()


    fun setCity(city: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    city = city
                )
            }
        }
    }

    private fun validCity(): Boolean {
        if (_state.value.city.isBlank() || _state.value.city.any { it.isDigit() }) {
            _state.update {
                it.copy(
                    inValidCity = true,
                    supportingText = R.string.city_no_digits
                )
            }
            return false
        } else {
            _state.update {
                it.copy(
                    inValidCity = false,
                    supportingText = R.string.enter_city_prompt
                )
            }
            return true
        }
    }


    fun onFindCityClick() {
        _weatherResult.value = NetworkResponse.Loading
        if (validCity()) {
            viewModelScope.launch {
                try {
                    val response = getDataByCityUseCase.get().execute(
                        city = _state.value.city,
                        languageCode = getLanguageUseCase.get().execute().languageCode
                    )
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _weatherResult.value = NetworkResponse.Success(it)
                        } ?: run {
                            _weatherResult.value = NetworkResponse.Error("No data found")
                        }
                    } else {
                        _weatherResult.value = NetworkResponse.Error("Error: ${response.message()}")
                    }
                } catch (e: Exception) {
                    _weatherResult.value = NetworkResponse.Error("Exception: ${e.message}")
                }
            }
        } else {
            _weatherResult.value = NetworkResponse.Error("In valid city")
        }
    }


    fun onBottomBarNavigationClick(route: Screens) {
        when (route) {
            Screens.SETTING_SCREEN -> viewModelScope.launch { _event.emit(HomeNavigationEvent.NavigationToSetting) }
            Screens.HOME_SCREEN -> {}
        }
    }
}