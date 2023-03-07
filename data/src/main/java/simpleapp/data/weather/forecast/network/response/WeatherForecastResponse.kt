package simpleapp.data.weather.forecast.network.response

import kotlinx.serialization.Serializable
import simpleapp.data.weather.network.response.MainResponse
import simpleapp.data.weather.network.response.WeatherResponse
import simpleapp.data.weather.network.response.WindResponse

@Serializable
data class WeatherForecastResponse(
    val mainResponse: MainResponse,
    val weatherListResponse: List<WeatherResponse>,
    val cloudsResponse: CloudsResponse,
    val dateResponse: DateResponse,
    val windResponse: WindResponse
)
