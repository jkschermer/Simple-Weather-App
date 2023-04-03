package nl.simpleapp.domain.weather.model

import java.io.Serializable

data class WeatherForecastData(
    val date: Long,
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
) : Serializable {

    fun toCelsius() : WeatherForecastData {
        return WeatherForecastData(date = date, weather = weather, main = main.toCelsius(), wind = wind)
    }
}