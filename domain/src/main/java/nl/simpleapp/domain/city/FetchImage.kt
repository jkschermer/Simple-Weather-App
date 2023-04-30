package nl.simpleapp.domain.city

import nl.simpleapp.domain.city.data.ImageRepository
import nl.simpleapp.domain.city.model.Image

class FetchImage(private val imageCityRepository: ImageRepository) {

    suspend operator fun invoke(name: String): Image? {
        return imageCityRepository.fetchImage(name)
    }
}