package nl.simpleapp.domain.weather.model

import java.io.Serializable
import kotlin.math.round

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
) : Serializable {
    fun toCelsius(): Main {
        val kelvin = 273.15
        val temp = this.temp - kelvin
        val tempFeelsLike = this.feels_like - kelvin
        val tempMin = this.temp_min - kelvin
        val tempMax = this.temp_max - kelvin

        return Main(
            temp = temp.mapToTwoDecimals(),
            feels_like = tempFeelsLike.mapToTwoDecimals(),
            temp_min = tempMin.mapToTwoDecimals(),
            temp_max = tempMax.mapToTwoDecimals(),
            pressure = pressure,
            humidity = humidity
        )
    }

    private fun Double.mapToTwoDecimals(): Double {
        return round(this * 10) / 10.0
    }
}




