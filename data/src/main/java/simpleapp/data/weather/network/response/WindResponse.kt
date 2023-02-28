package simpleapp.data.weather.network.response

import kotlinx.serialization.Serializable

@Serializable
data class WindResponse(
    val speed: Double,
    val deg: Int,
)
