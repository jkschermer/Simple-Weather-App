package simpleapp.data.city.network

import nl.simpleapp.domain.city.model.Image
import simpleapp.data.city.network.response.ImageWrapperResponse

object ImageResponseMapper {

    fun ImageWrapperResponse.toImage(): Image {

        return hits.random().let {
            Image(
                id = it.id,
                name = it.tags,
                imageUrl = it.webformatURL,
                likes = it.likes
            )
        }
    }
}