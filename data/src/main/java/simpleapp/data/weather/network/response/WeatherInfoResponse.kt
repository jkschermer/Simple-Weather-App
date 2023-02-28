package simpleapp.data.weather.network.response

import kotlinx.serialization.Serializable

@Serializable
data class WeatherInfoResponse(
    val main: MainResponse,
    val wind: WindResponse,
    val weather: List<WeatherResponse>,
    val name: String,
)
