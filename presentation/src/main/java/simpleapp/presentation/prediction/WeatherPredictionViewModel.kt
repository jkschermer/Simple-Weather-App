package simpleapp.presentation.prediction

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import nl.simpleapp.domain.FetchWeatherForecast
import simpleapp.presentation.generic.UIState
import simpleapp.presentation.navigation.WeatherNavigationEvent

class WeatherPredictionViewModel(
    private val fetchWeatherForecast: FetchWeatherForecast
) : ViewModel() {

    private val _state = MutableStateFlow(UIState.LOADING)
    val state = _state.asStateFlow()

    private val _weatherPrediction = MutableStateFlow<WeatherPredictionUIModel?>(null)
    val weatherPrediction: StateFlow<WeatherPredictionUIModel?> = _weatherPrediction

    private val _navigation = MutableSharedFlow<WeatherNavigationEvent>()
    val navigation: SharedFlow<WeatherNavigationEvent> = _navigation

    private suspend fun setupWeatherPrediction(city: String) {
        try {
            _weatherPrediction.value =
                WeatherPredictionUIMapper.mapToUIModel(fetchWeatherForecast(city))
            _state.value = UIState.NORMAL
        } catch (e: Exception) {
            Log.e("error message", e.toString())
            _state.value = UIState.ERROR
        }
    }

    fun getWeatherPrediction(city: String) {
        viewModelScope.launch {
            _state.value = UIState.LOADING
            setupWeatherPrediction(city)
        }
    }

    fun navigateBack() {
        viewModelScope.launch {
            _navigation.emit(WeatherNavigationEvent.NavigateBackToMain)
        }
    }
}