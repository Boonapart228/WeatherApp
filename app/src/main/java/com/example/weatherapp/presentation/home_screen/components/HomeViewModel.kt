package com.example.weatherapp.presentation.home_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
import com.example.weatherapp.domain.models.NetworkResponse
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.WeatherStoreRepository
import com.example.weatherapp.domain.usecase.date_handler.GetShortDayNameUseCase
import com.example.weatherapp.domain.usecase.location.GetCurrentLocationUseCase
import com.example.weatherapp.domain.usecase.weather.GetWeatherByCityUseCase
import com.example.weatherapp.domain.usecase.weather.GetWeatherByLocationUseCase
import com.example.weatherapp.domain.usecase.weather.GetWeatherLocationName
import com.example.weatherapp.domain.usecase.weather_validator.HandleInvalidCityFormatUseCase
import com.example.weatherapp.presentation.home_screen.model.PermissionEvent
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
    private val getCurrentLocationUseCase: Provider<GetCurrentLocationUseCase>,
    private val getWeatherByLocationUseCase: Provider<GetWeatherByLocationUseCase>,
    private val getWeatherByCityUseCase: Provider<GetWeatherByCityUseCase>,
    private val handleInvalidCityFormatUseCase: Provider<HandleInvalidCityFormatUseCase>,
    private val weatherStoreRepository: WeatherStoreRepository,
    private val getWeatherLocationName: Provider<GetWeatherLocationName>,
    private val getShortDayNameUseCase: Provider<GetShortDayNameUseCase>
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())

    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<HomeNavigationEvent>()

    val event = _event.asSharedFlow()

    private val _permissionEventEvent = MutableSharedFlow<PermissionEvent>()

    val permissionEvent = _permissionEventEvent.asSharedFlow()

    private val _weatherResult =
        MutableStateFlow<NetworkResponse<WeatherModel>>(NetworkResponse.Default)

    val weatherResult: StateFlow<NetworkResponse<WeatherModel>> = _weatherResult.asStateFlow()

    init {
        getWeatherResult()
    }

    private fun getWeatherResult() {
        viewModelScope.launch {
            _weatherResult.value = weatherStoreRepository.getWeatherResponse()
        }
    }

    fun onFindWeatherByLocation() {
        setCurrentLocation()
    }

    private fun setCurrentLocation() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    currentLocation = getCurrentLocationUseCase.get().execute()
                )
            }
            findWeatherByLocation("${_state.value.currentLocation?.latitude}, ${_state.value.currentLocation?.longitude}")
        }
    }

    private fun findWeatherByLocation(location: String) {
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            _weatherResult.value = getWeatherByLocationUseCase.get().execute(location)
        }
    }

    fun setCity(city: String) {
        viewModelScope.launch {
            if (city.isValidCityInput() || city.isEmpty()) {
                _state.update {
                    it.copy(
                        city = city
                    )
                }
            }
        }
    }

    private fun String.isValidCityInput(): Boolean {
        return this.isNotEmpty() && (this.last().isLetter() || this.last() == ' ')
    }

    private fun validCity(): Boolean {
        if (_state.value.city.any { it.isDigit() }) {
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


    fun onFindWeatherByCityClick() {
        _weatherResult.value = NetworkResponse.Loading
        if (validCity()) {
            viewModelScope.launch {
                _weatherResult.value = getWeatherByCityUseCase.get().execute(_state.value.city)
            }
        } else {
            viewModelScope.launch {
                _weatherResult.value = handleInvalidCityFormatUseCase.get().execute()
            }
        }
    }

    fun onLocalePermissionClick(permissionEvent: PermissionEvent) {
        when (permissionEvent) {
            PermissionEvent.LocationPermissionEvent -> viewModelScope.launch {
                _permissionEventEvent.emit(
                    PermissionEvent.LocationPermissionEvent
                )
            }
        }
    }

    fun onBottomBarNavigationClick(route: Screens) {
        when (route) {
            Screens.SETTING_SCREEN -> viewModelScope.launch { _event.emit(HomeNavigationEvent.NavigationToSetting) }
            Screens.HOME_SCREEN -> {}
        }
    }

    fun onToggleVisibility() {
        viewModelScope.launch {
            _state.update {
                it.copy(isTextFullyVisible = !it.isTextFullyVisible)
            }
        }
    }

    fun onRefreshWeatherClick() {
        getWeatherLocationName.get().execute()?.let {
            _weatherResult.value = NetworkResponse.Loading
            viewModelScope.launch {
                _weatherResult.value = getWeatherByCityUseCase.get()
                    .execute(it)
            }
        }
    }

    fun getShortDayName(dataEpoch: Int): Int {
        return getShortDayNameUseCase.get().execute(dataEpoch)
    }
}