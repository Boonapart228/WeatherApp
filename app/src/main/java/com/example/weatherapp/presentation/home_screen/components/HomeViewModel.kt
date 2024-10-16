package com.example.weatherapp.presentation.home_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.models.NetworkResponse
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.usecase.GetDataByCityUseCase
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
    private val getDataByCityUseCase: Provider<GetDataByCityUseCase>
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


    fun onFindCityClick() {
        //Потім переписати
        if (_state.value.city.isBlank() || _state.value.city.isEmpty()) {
            _weatherResult.value = NetworkResponse.Error("City is not set")
            return
        }
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = getDataByCityUseCase.get().execute(city = _state.value.city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.location.name.isBlank()) {
                            _weatherResult.value = NetworkResponse.Error("City not found")
                        } else {
                            _weatherResult.value = NetworkResponse.Success(it)
                        }
                    } ?: run {
                        _weatherResult.value = NetworkResponse.Error("Empty response body")
                    }
                } else {
                    _weatherResult.value = NetworkResponse.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.Error("Exception: ${e.message}")
            }
        }
    }


    fun onBottomBarNavigationClick(route: Screens) {
        when (route) {
            Screens.SETTING_SCREEN -> viewModelScope.launch { _event.emit(HomeNavigationEvent.NavigationToSetting) }
            Screens.HOME_SCREEN -> {}
        }
    }
}