package simpleapp.data.weather.forecast.network.response

import kotlinx.serialization.Serializable

@Serializable
data class CloudsResponse(
    val all: Int
)
