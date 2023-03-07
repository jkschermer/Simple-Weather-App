package nl.simpleapp.domain


import nl.simpleapp.domain.weather.data.WeatherRepository
import nl.simpleapp.domain.weather.model.CurrentWeatherData

class FetchWeather(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(city: String): CurrentWeatherData {
        return weatherRepository.fetchWeather(city).toCelsius()
    }
}
