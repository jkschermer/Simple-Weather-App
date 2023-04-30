package simpleapp.data.city.network.response

import kotlinx.serialization.Serializable

@Serializable
data class ImageWrapperResponse(
  val total: Long,
  val totalHits: Long,
  val hits: List<ImageResponse>
)