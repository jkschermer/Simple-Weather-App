package nl.simpleapp.domain


import nl.simpleapp.domain.weather.data.ReturnWeatherRepository
import nl.simpleapp.domain.weather.model.WeatherInfo

class FetchWeather(private val weatherRepository: ReturnWeatherRepository) {

    suspend operator fun invoke(city: String): WeatherInfo {
        return weatherRepository.fetchWeather(city).toCelsius()
    }
}
