package nl.simpleapp.domain.weather.data

import nl.simpleapp.domain.weather.model.WeatherInfo

interface ReturnWeatherRepository {

    suspend fun fetchWeather(city: String) : WeatherInfo
}