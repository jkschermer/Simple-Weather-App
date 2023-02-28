package simpleapp.data.weather.network

import nl.simpleapp.domain.weather.model.Main
import nl.simpleapp.domain.weather.model.Weather
import nl.simpleapp.domain.weather.model.WeatherInfo
import nl.simpleapp.domain.weather.model.Wind
import simpleapp.data.weather.network.response.MainResponse
import simpleapp.data.weather.network.response.WeatherInfoResponse
import simpleapp.data.weather.network.response.WeatherResponse
import simpleapp.data.weather.network.response.WindResponse

object WeatherResponseMapper {

    fun WeatherInfoResponse.toWeatherInfo(): WeatherInfo {
        return WeatherInfo(
            main = main.toMain(),
            wind = wind.toWind(),
            weather = weather.toWeather(),
            name = name
        )
    }

    private fun MainResponse.toMain(): Main {
        return Main(
            temp = temp,
            feels_like = feelsLike,
            temp_min = tempMin,
            temp_max = tempMax,
            pressure = pressure,
            humidity = humidity
        )
    }

    private fun WindResponse.toWind(): Wind {
        return Wind(speed = speed, deg = deg)
    }

    private fun List<WeatherResponse>.toWeather(): List<Weather> {
        return this.map { weatherResponse ->
            Weather(
                id = weatherResponse.id,
                main = weatherResponse.main,
                icon = weatherResponse.icon,
                description = weatherResponse.description
            )
        }
    }
}