package simpleapp.presentation.weather

import nl.simpleapp.domain.weather.model.CurrentWeatherData

object WeatherInfoUIMapper {

    private const val WEATHER_SEPARATOR = " "
    private const val CELSIUS_SYMBOL = "°"
    private const val PERCENTAGE_SYMBOL = "%"

    fun mapToUIModel(currentWeatherData: CurrentWeatherData): WeatherInfoUIModel {
        return WeatherInfoUIModel(
            temperature = currentWeatherData.main.temp.toDisplayString(),
            feelsLike = currentWeatherData.main.feels_like.toDisplayString(),
            minTemp = currentWeatherData.main.temp_min.toDisplayString(),
            maxTemp = currentWeatherData.main.temp_max.toDisplayString(),
            wind = categorizeWind(currentWeatherData.wind.speed),
            humidity = currentWeatherData.main.humidity.toString().plus(PERCENTAGE_SYMBOL),
            main = currentWeatherData.weather.joinToString(separator = WEATHER_SEPARATOR) { weather -> weather.main.replaceFirstChar { it.uppercase() } },
            description = currentWeatherData.weather.joinToString(separator = WEATHER_SEPARATOR) { weather -> weather.description.replaceFirstChar { it.uppercase() } },
            icon = currentWeatherData.weather.joinToString(separator = WEATHER_SEPARATOR) { it.icon.addIconToUrl() },
            name = currentWeatherData.name
        )
    }

    fun Double.toDisplayString(): String {
        return this.toString().plus(CELSIUS_SYMBOL)
    }

    fun String.addIconToUrl(): String {
        return "https://openweathermap.org/img/wn/$this@2x.png"
    }

    private fun categorizeWind(speed: Double) : String {
        return when {
            speed < 1.0 -> "Calm"
            speed < 3.0 -> "Light air"
            speed < 7.0 -> "Light breeze"
            speed < 12.0 -> "Gentle breeze"
            speed < 18.0 -> "Moderate breeze"
            speed < 24.0 -> "Fresh breeze"
            speed < 31.0 -> "Strong breeze"
            speed < 38.0 -> "Near gale"
            speed < 46.0 -> "Gale"
            speed < 54.0 -> "Strong gale"
            speed < 63.0 -> "Storm"
            speed < 72.0 -> "Violent storm"
            else -> "Hurricane force"
        }
    }
}