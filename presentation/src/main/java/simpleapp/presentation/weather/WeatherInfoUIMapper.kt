package simpleapp.presentation.weather

import nl.simpleapp.domain.weather.model.WeatherInfo

object WeatherInfoUIMapper {

    private const val WEATHER_SEPARATOR = " "
    private const val CELSIUS_SYMBOL = "°"

    fun mapToUIModel(weatherInfo: WeatherInfo): WeatherInfoUIModel {
        return WeatherInfoUIModel(
            temperature = weatherInfo.main.temp.toDisplayString(),
            feelsLike = weatherInfo.main.feels_like.toDisplayString(),
            minTemp = weatherInfo.main.temp_min.toDisplayString(),
            maxTemp = weatherInfo.main.temp_max.toDisplayString(),
            humidity = weatherInfo.main.humidity.toString(),
            main = weatherInfo.weather.joinToString(separator = WEATHER_SEPARATOR) { weather -> weather.main.replaceFirstChar { it.uppercase() } },
            description = weatherInfo.weather.joinToString(separator = WEATHER_SEPARATOR) { weather -> weather.description.replaceFirstChar { it.uppercase() } },
            icon = weatherInfo.weather.joinToString(separator = WEATHER_SEPARATOR) { it.icon.addIconToUrl() },
            name = weatherInfo.name
        )
    }

    private fun Double.toDisplayString(): String {
        return this.toString().plus(CELSIUS_SYMBOL)
    }

    private fun String.addIconToUrl(): String {
        return "https://openweathermap.org/img/wn/$this@2x.png"
    }
}