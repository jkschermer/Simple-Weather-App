package nl.simpleapp.domain


import nl.simpleapp.domain.weather.data.WeatherForecastRepository
import nl.simpleapp.domain.weather.model.WeatherForecastData
import java.util.*
import java.util.concurrent.TimeUnit

class FetchWeatherForecast(private val weatherForecastRepository: WeatherForecastRepository) {

    suspend operator fun invoke(city: String): List<WeatherForecastData> {
        val weatherForecast = weatherForecastRepository.fetchWeatherForecast(city)
        val now = Date()
        val fiveDaysFromNow = Date(now.time + TimeUnit.DAYS.toMillis(5))
        return weatherForecast
    }
}