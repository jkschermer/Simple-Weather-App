package simpleapp.presentation.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import nl.simpleapp.domain.time.FetchDate
import nl.simpleapp.domain.weather.current.FetchWeather
import simpleapp.presentation.generic.UIState
import simpleapp.presentation.navigation.WeatherNavigationEvent
import simpleapp.presentation.weather.current.model.DateUIModel
import simpleapp.presentation.weather.current.model.WeatherInfoUIModel

class WeatherViewModel(
    private val fetchWeather: FetchWeather,
    private val fetchDate: FetchDate,
) : ViewModel() {

    private val _city = MutableStateFlow("")
    val city: StateFlow<String> = _city

    private val _state = MutableStateFlow(UIState.NORMAL)
    val state = _state.asStateFlow()

    private val _weather = MutableStateFlow<WeatherInfoUIModel?>(null)
    val weather: StateFlow<WeatherInfoUIModel?> = _weather

    private val _navigation = MutableSharedFlow<WeatherNavigationEvent>()
    val navigation: SharedFlow<WeatherNavigationEvent> = _navigation

    private val _date = MutableStateFlow<DateUIModel?>(null)
    val date: StateFlow<DateUIModel?> = _date

    private suspend fun setupWeather(city: String) {
        try {
            _weather.value = WeatherInfoUIMapper.mapToUIModel(fetchWeather(city))
            _date.value = DateUIMapper.mapToUIModel(fetchDate())
            _state.value = UIState.NORMAL
        } catch (e: Exception) {
            _state.value = UIState.ERROR
        }
    }

    fun city(city: String) {
        _city.value = city
    }

    fun getWeather(city: String) {
        viewModelScope.launch {
            _state.value = UIState.LOADING
            setupWeather(city)
        }
    }

    fun navigateToWeatherPrediction() {
        viewModelScope.launch {
            _navigation.emit(WeatherNavigationEvent.OpenWeatherPredictionScreen)
        }
    }
}
