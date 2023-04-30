package simpleapp.data.city.network

import nl.simpleapp.domain.city.data.ImageRepository
import nl.simpleapp.domain.city.model.Image
import simpleapp.data.city.network.ImageResponseMapper.toImage

class RemoteImageRepository(private val imageService: ImageService) : ImageRepository {

    override suspend fun fetchImage(name: String): Image? {
        return imageService.fetchImage(name).toImage()
    }
}