package simpleapp.data.weather.network

import android.util.Log
import simpleapp.data.generic.network.HttpClients
import simpleapp.data.weather.network.response.WeatherInfoResponse

class ReturnWeatherService(private val httpClients: HttpClients) {

    private val apiKey = "ba6504c4483f561f540183bd459a0ce6"

    suspend fun fetchWeather(city: String): WeatherInfoResponse {
        val baseApiUrl = "https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}"
        val result = httpClients.unauthorizedGet<WeatherInfoResponse>(baseApiUrl)

        try {
            return result
        } catch (exception: Exception) {
            Log.i("Error message", exception.toString())
        }
        return result
    }
}