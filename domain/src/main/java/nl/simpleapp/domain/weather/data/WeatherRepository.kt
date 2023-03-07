package nl.simpleapp.domain.weather.data

import nl.simpleapp.domain.weather.model.CurrentWeatherData

interface WeatherRepository {

    suspend fun fetchWeather(city: String) : CurrentWeatherData
}