package simpleapp.data.weather.forecast.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import simpleapp.data.weather.network.response.MainResponse
import simpleapp.data.weather.network.response.WeatherResponse
import simpleapp.data.weather.network.response.WindResponse

@Serializable
data class WeatherForecastDataResponse(
    @SerialName("dt")
    val date: Long,
    val clouds: CloudsResponse,
    val main: MainResponse,
    val wind: WindResponse,
    val weather: List<WeatherResponse>
)
