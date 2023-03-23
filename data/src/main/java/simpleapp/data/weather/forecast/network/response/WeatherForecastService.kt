package simpleapp.data.weather.forecast.network.response

import android.util.Log
import simpleapp.data.BuildConfig
import simpleapp.data.generic.network.HttpClients

class WeatherForecastService(private val httpClients: HttpClients) {

    suspend fun fetchWeatherForecast(city: String): WeatherForecastResponse {
        val baseApiUrl =
            "https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${BuildConfig.API_KEY}"
        val result = httpClients.unauthorizedGet<WeatherForecastResponse>(baseApiUrl)

        try {
            Log.d("API RESPONSE", result.toString())
            return result
        } catch (exception: Exception) {
            Log.i("Error message", exception.toString())
            throw exception
        }
    }
}