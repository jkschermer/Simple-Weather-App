package nl.simpleapp.domain.city.data

import nl.simpleapp.domain.city.model.Image

interface ImageRepository {

    suspend fun fetchImage(name: String) : Image?
}