package simpleapp.presentation.weather

import nl.simpleapp.domain.weather.model.CurrentWeatherData

object WeatherInfoUIMapper {

    private const val WEATHER_SEPARATOR = " "
    private const val CELSIUS_SYMBOL = "°"

    fun mapToUIModel(currentWeatherData: CurrentWeatherData): WeatherInfoUIModel {
        return WeatherInfoUIModel(
            temperature = currentWeatherData.main.temp.toDisplayString(),
            feelsLike = currentWeatherData.main.feels_like.toDisplayString(),
            minTemp = currentWeatherData.main.temp_min.toDisplayString(),
            maxTemp = currentWeatherData.main.temp_max.toDisplayString(),
            humidity = currentWeatherData.main.humidity.toString(),
            main = currentWeatherData.weather.joinToString(separator = WEATHER_SEPARATOR) { weather -> weather.main.replaceFirstChar { it.uppercase() } },
            description = currentWeatherData.weather.joinToString(separator = WEATHER_SEPARATOR) { weather -> weather.description.replaceFirstChar { it.uppercase() } },
            icon = currentWeatherData.weather.joinToString(separator = WEATHER_SEPARATOR) { it.icon.addIconToUrl() },
            name = currentWeatherData.name
        )
    }

    private fun Double.toDisplayString(): String {
        return this.toString().plus(CELSIUS_SYMBOL)
    }

    private fun String.addIconToUrl(): String {
        return "https://openweathermap.org/img/wn/$this@2x.png"
    }
}