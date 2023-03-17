package simpleapp.presentation.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import nl.simpleapp.domain.FetchWeather
import nl.simpleapp.domain.FetchWeatherForecast
import nl.simpleapp.domain.time.FetchDate
import simpleapp.presentation.generic.UIState
import simpleapp.presentation.navigation.WeatherNavigationEvent

class WeatherViewModel(
    private val fetchWeather: FetchWeather,
    private val fetchDate: FetchDate,
    private val fetchWeatherForecast: FetchWeatherForecast,
) : ViewModel() {

    private val _state = MutableStateFlow(UIState.NORMAL)
    val state = _state.asStateFlow()

    private val _weather = MutableStateFlow<WeatherInfoUIModel?>(null)
    val weather: StateFlow<WeatherInfoUIModel?> = _weather

    private val _weatherPrediction = MutableStateFlow<WeatherPredictionUIModel?>(null)
    val weatherPrediction: StateFlow<WeatherPredictionUIModel?> = _weatherPrediction

    private val _navigation = MutableSharedFlow<WeatherNavigationEvent>()
    val navigation: SharedFlow<WeatherNavigationEvent> = _navigation

    private val _date = MutableStateFlow<DateUIModel?>(null)
    val date: StateFlow<DateUIModel?> = _date

    private suspend fun setupWeather(city: String) {
        try {
            _weather.value = WeatherInfoUIMapper.mapToUIModel(fetchWeather(city))
            _date.value = DateUIMapper.mapToUIModel(fetchDate.invoke())
            _state.value = UIState.NORMAL
        } catch (e: Exception) {
            _state.value = UIState.ERROR
        }
    }

    private suspend fun setupWeatherPrediction(city: String) {
        try {
            _weatherPrediction.value =
                WeatherPredictionUIMapper.mapToUIModel(fetchWeatherForecast(city))
            _state.value = UIState.NORMAL
        } catch (e: Exception) {
            _state.value = UIState.ERROR
        }
    }

    fun getWeather(city: String) {
        viewModelScope.launch {
            _state.value = UIState.LOADING
            setupWeather(city)
        }
    }

    fun getWeatherPrediction(city: String) {
        viewModelScope.launch {
            _state.value = UIState.LOADING
        }
    }

    fun navigateBack() {
        viewModelScope.launch {
            _navigation.emit(WeatherNavigationEvent.NavigateBackToMain)
        }
    }

    fun navigateToWeatherPrediction(city: String) {
        viewModelScope.launch {
            _navigation.emit(WeatherNavigationEvent.OpenWeatherPredictionScreen)
            setupWeatherPrediction(city)
        }
    }
}
