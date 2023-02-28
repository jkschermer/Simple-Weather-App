package simpleapp.data.weather.network

import nl.simpleapp.domain.weather.data.ReturnWeatherRepository
import nl.simpleapp.domain.weather.model.WeatherInfo
import simpleapp.data.weather.network.WeatherResponseMapper.toWeatherInfo

class RemoteReturnWeatherRepository(
    private val service: ReturnWeatherService
) : ReturnWeatherRepository {

    override suspend fun fetchWeather(city: String): WeatherInfo {
        return service.fetchWeather(city).toWeatherInfo()
    }
}
