package simpleapp.presentation.prediction

import nl.simpleapp.domain.weather.model.WeatherForecastData
import simpleapp.presentation.weather.WeatherInfoUIMapper.toDisplayString

object WeatherPredictionUIMapper {

    private const val WEATHER_SEPARATOR = " "

    fun mapToUIModel(weatherForecastData: List<WeatherForecastData>): WeatherPredictionUIModel {
        return WeatherPredictionUIModel(
            minTemp = weatherForecastData.minOf { it.main.temp_min.toDisplayString() },
            maxTemp = weatherForecastData.maxOf { it.main.temp_max.toDisplayString() },
            icon = weatherForecastData.joinToString { it.weather[0].icon })
    }
}
