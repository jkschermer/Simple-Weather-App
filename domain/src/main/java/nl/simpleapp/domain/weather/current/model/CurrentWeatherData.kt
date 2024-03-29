package nl.simpleapp.domain.weather.current.model

import nl.simpleapp.domain.weather.model.Main
import nl.simpleapp.domain.weather.model.Weather
import nl.simpleapp.domain.weather.model.Wind
import java.io.Serializable

data class CurrentWeatherData(
    val main: Main,
    val wind: Wind,
    val weather: List<Weather>,
    val name: String
) : Serializable {
    fun toCelsius(): CurrentWeatherData {
        return CurrentWeatherData(main = main.toCelsius(), wind = wind, weather = weather, name = name)
    }
}