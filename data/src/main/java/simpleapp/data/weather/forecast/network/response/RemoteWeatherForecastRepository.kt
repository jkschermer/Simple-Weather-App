package simpleapp.data.weather.forecast.network.response

import nl.simpleapp.domain.weather.data.WeatherForecastRepository
import nl.simpleapp.domain.weather.model.WeatherForecastData
import simpleapp.data.weather.forecast.network.response.WeatherForecastMapper.toWeatherForecast

class RemoteWeatherForecastRepository(private val weatherForecastService: WeatherForecastService) :
    WeatherForecastRepository {

    override suspend fun fetchWeatherForecast(city: String): List<WeatherForecastData> {
        return weatherForecastService.fetchWeatherForecast(city).toWeatherForecast()
    }
}