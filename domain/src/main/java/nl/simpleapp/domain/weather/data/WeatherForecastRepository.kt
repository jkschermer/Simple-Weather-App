package nl.simpleapp.domain.weather.data

import nl.simpleapp.domain.weather.model.WeatherForecastData

interface WeatherForecastRepository {

    suspend fun fetchWeatherForecast(city: String) : List<WeatherForecastData>
}