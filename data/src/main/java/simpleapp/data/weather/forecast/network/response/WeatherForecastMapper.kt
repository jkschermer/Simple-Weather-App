package simpleapp.data.weather.forecast.network.response

import nl.simpleapp.domain.weather.model.WeatherForecastData
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
