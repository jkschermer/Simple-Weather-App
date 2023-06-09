package simpleapp.data.city.network

import android.util.Log
import simpleapp.data.BuildConfig
import simpleapp.data.city.network.response.ImageWrapperResponse
import simpleapp.data.generic.HttpClients

class ImageService(private val httpClients: HttpClients) {

    suspend fun fetchImage(name: String): ImageWrapperResponse {
        val baseApiUrl = "https://pixabay.com/api/?key=${BuildConfig.API_KEY_IMAGES}&q=${name}}&image_type=photo"
        val result = httpClients.unauthorizedGet<ImageWrapperResponse>(baseApiUrl)

        try {
            return result
        } catch (exception: Exception) {
            Log.i("Error message", exception.toString())
        }
        return result
    }
}