package simpleapp.data.weather.forecast.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DateResponse(
    @SerialName("dt_text")
    val dateText: String,
    @SerialName("dt")
    val dateLong: Long
)
