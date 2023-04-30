package simpleapp.presentation.image

import nl.simpleapp.domain.city.model.Image

object ImageUIMapper {

    fun mapToUIModel(image: Image) : ImageUIModel {
        return ImageUIModel(image.imageUrl)
    }
}