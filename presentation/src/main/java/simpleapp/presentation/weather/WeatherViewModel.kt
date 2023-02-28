package simpleapp.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nl.simpleapp.domain.FetchWeather
import nl.simpleapp.domain.time.FetchDate
import simpleapp.presentation.generic.SingleLiveEvent
import simpleapp.presentation.generic.UIState
import simpleapp.presentation.navigation.WeatherNavigationAction

class WeatherViewModel(
    private val fetchWeather: FetchWeather,
    private val fetchDate: FetchDate,
) : ViewModel() {

    private val _state = MutableStateFlow(UIState.NORMAL)
    val state = _state.asStateFlow()

    private val _navigation = SingleLiveEvent<WeatherNavigationAction>()
    val navigation: LiveData<WeatherNavigationAction> = _navigation

    private val _weather = MutableStateFlow<WeatherInfoUIModel?>(null)
    val weather: StateFlow<WeatherInfoUIModel?> = _weather

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

    fun getWeather(city: String) {
        viewModelScope.launch {
            _state.value = UIState.LOADING
            setupWeather(city)
        }
    }

//    fun onItemClicked(weatherInfo: WeatherInfo) {
//        _navigation.postValue(WeatherNavigationAction.OpenDetailWind(weatherInfo.main))
//    }
}