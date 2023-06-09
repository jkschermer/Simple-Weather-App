package simpleapp.data.weather.forecast.network

import nl.simpleapp.domain.weather.forecast.model.WeatherForecastData
import simpleapp.data.weather.forecast.network.response.WeatherForecastResponse
import simpleapp.data.weather.network.WeatherResponseMapper.toMain
import simpleapp.data.weather.network.WeatherResponseMapper.toWeather
import simpleapp.data.weather.network.WeatherResponseMapper.toWind

object WeatherForecastMapper {

    fun WeatherForecastResponse.toWeatherForecast(): List<WeatherForecastData> {
        return weatherForecastList.map {
            WeatherForecastData(
                date = it.date,
                weather = it.weather.toWeather(),
                main = it.main.toMain(),
                wind = it.wind.toWind()
            )
        }
    }
}
