package nl.simpleapp.domain.weather.model

import java.io.Serializable

data class WeatherInfo(
    val main: Main,
    val wind: Wind,
    val weather: List<Weather>,
    val name: String
) : Serializable {
    fun toCelsius(): WeatherInfo {
        return WeatherInfo(main = main.toCelsius(), wind = wind, weather = weather, name = name)
    }
}