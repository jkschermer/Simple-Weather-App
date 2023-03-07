package simpleapp.data.weather.forecast.network.response

import nl.simpleapp.domain.weather.model.WeatherForecastData
import simpleapp.data.weather.network.WeatherResponseMapper.toMain
import simpleapp.data.weather.network.WeatherResponseMapper.toWeather
import simpleapp.data.weather.network.WeatherResponseMapper.toWind
import java.util.*

object WeatherForecastMapper {

    fun WeatherForecastListResponse.toWeatherForecast(): List<WeatherForecastData> {
        return this.weatherForecastList.map { forecastResponse ->
            WeatherForecastData(
                date = forecastResponse.dateResponse.toDate(),
                weather = forecastResponse.weatherListResponse.toWeather(),
                main = forecastResponse.mainResponse.toMain(),
                wind = forecastResponse.windResponse.toWind()
            )
        }
    }

    private fun DateResponse.toDate(): Date {
        return Date(this.dateLong)
    }
}