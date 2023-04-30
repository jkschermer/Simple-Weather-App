package nl.simpleapp.domain.city.model

import java.io.Serializable

data class Image(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val likes: Int,
) : Serializable