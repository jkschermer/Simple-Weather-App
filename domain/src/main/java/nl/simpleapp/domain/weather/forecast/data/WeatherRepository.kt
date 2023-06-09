package nl.simpleapp.domain.weather.forecast.data

import nl.simpleapp.domain.weather.current.model.CurrentWeatherData

interface WeatherRepository {

    suspend fun fetchWeather(city: String) : CurrentWeatherData
}