package simpleapp.data.weather.network

import android.util.Log
import simpleapp.data.BuildConfig
import simpleapp.data.generic.HttpClients
import simpleapp.data.weather.network.response.WeatherInfoResponse

class WeatherService(private val httpClients: HttpClients) {

    suspend fun fetchWeather(city: String): WeatherInfoResponse {
        val baseApiUrl = "https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${BuildConfig.API_KEY}"
        val result = httpClients.unauthorizedGet<WeatherInfoResponse>(baseApiUrl)

        try {
            return result
        } catch (exception: Exception) {
            Log.i("Error message", exception.toString())
        }
        return result
    }
}