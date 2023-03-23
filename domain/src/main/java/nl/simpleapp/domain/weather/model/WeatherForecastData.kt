package nl.simpleapp.domain.weather.model

import java.io.Serializable
import java.util.*

data class WeatherForecastData(
    val date: Date,
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
) : Serializable