package nl.simpleapp.domain.weather.current


import nl.simpleapp.domain.weather.forecast.data.WeatherRepository
import nl.simpleapp.domain.weather.current.model.CurrentWeatherData

class FetchWeather(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(city: String): CurrentWeatherData {
        return weatherRepository.fetchWeather(city).toCelsius()
    }
}
