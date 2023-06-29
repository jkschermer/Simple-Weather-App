package simpleapp.presentation.weather.prediction

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import nl.simpleapp.domain.weather.forecast.FetchWeatherForecast
import nl.simpleapp.domain.city.FetchImage
import simpleapp.presentation.generic.UIState
import simpleapp.presentation.image.ImageUIMapper
import simpleapp.presentation.image.ImageUIModel
import simpleapp.presentation.navigation.WeatherNavigationEvent
import simpleapp.presentation.weather.prediction.model.WeatherPredictionUIModel

class WeatherPredictionViewModel(
    private val fetchWeatherForecast: FetchWeatherForecast,
    private val fetchImage: FetchImage
) : ViewModel() {

    private val _state = MutableStateFlow(UIState.NORMAL)
    val state = _state.asStateFlow()

    private val _weatherPrediction = MutableStateFlow<List<WeatherPredictionUIModel>>(emptyList())
    val weatherPrediction: StateFlow<List<WeatherPredictionUIModel>> = _weatherPrediction

    private val _imageUIModel = MutableStateFlow<ImageUIModel?>(null)
    val imageUIModel: StateFlow<ImageUIModel?> = _imageUIModel

    private val _navigation = MutableSharedFlow<WeatherNavigationEvent>()
    val navigation: SharedFlow<WeatherNavigationEvent> = _navigation

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun getWeatherPrediction(city: String) {
        viewModelScope.launch {
            _state.value = UIState.LOADING
            setupWeatherPrediction(city)
        }
    }

    private suspend fun setupCityImage(city: String) {
        try {
            _imageUIModel.value = fetchImage.invoke(city)?.let { ImageUIMapper.mapToUIModel(it) }
            _state.value = UIState.NORMAL
        } catch (exception: Exception) {
            Log.e("error message", exception.toString())
            _state.value = UIState.ERROR
        }
    }

    suspend fun getCityImage(city: String) {
        viewModelScope.launch {
            _state.value = UIState.LOADING
            setupCityImage(city)
        }
    }

    fun navigateBack() {
        viewModelScope.launch {
            _navigation.emit(WeatherNavigationEvent.NavigateBackToMain)
        }
    }
}