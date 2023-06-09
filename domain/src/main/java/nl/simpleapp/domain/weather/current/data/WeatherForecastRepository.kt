package nl.simpleapp.domain.weather.current.data

import nl.simpleapp.domain.weather.forecast.model.WeatherForecastData

interface WeatherForecastRepository {

    suspend fun fetchWeatherForecast(city: String) : List<WeatherForecastData>
}