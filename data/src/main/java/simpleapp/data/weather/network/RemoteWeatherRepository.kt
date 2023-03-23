package simpleapp.data.weather.network

import nl.simpleapp.domain.weather.data.WeatherRepository
import nl.simpleapp.domain.weather.model.CurrentWeatherData
import simpleapp.data.weather.network.WeatherResponseMapper.toWeatherInfo

class RemoteWeatherRepository(
    private val service: WeatherService
) : WeatherRepository {

    override suspend fun fetchWeather(city: String): CurrentWeatherData {
        return service.fetchWeather(city).toWeatherInfo()
    }
}
