package simpleapp.data.weather.forecast.network

import nl.simpleapp.domain.weather.current.data.WeatherForecastRepository
import nl.simpleapp.domain.weather.forecast.model.WeatherForecastData
import simpleapp.data.weather.forecast.network.WeatherForecastMapper.toWeatherForecast

class RemoteWeatherForecastRepository(private val weatherForecastService: WeatherForecastService) :
    WeatherForecastRepository {

    override suspend fun fetchWeatherForecast(city: String): List<WeatherForecastData> {
        return weatherForecastService.fetchWeatherForecast(city).toWeatherForecast()
    }
}