package simpleapp.data.weather.forecast.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastResponse(
    @SerialName("list")
    val weatherForecastList: List<WeatherForecastDataResponse>
)
