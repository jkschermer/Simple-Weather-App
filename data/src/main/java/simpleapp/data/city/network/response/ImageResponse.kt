package simpleapp.data.city.network.response

import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    val id: Long,
    val tags: String,
    val webformatURL: String,
    val likes: Int,
)
