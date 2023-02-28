package simpleapp.data.weather.network.response

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)


