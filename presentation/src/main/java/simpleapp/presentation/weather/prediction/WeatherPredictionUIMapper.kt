package simpleapp.presentation.weather.prediction

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import nl.simpleapp.domain.weather.forecast.model.WeatherForecastData
import simpleapp.presentation.weather.current.WeatherInfoUIMapper.addIconToUrl
import simpleapp.presentation.weather.current.WeatherInfoUIMapper.toDisplayString
import simpleapp.presentation.weather.prediction.model.WeatherPredictionUIModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object WeatherPredictionUIMapper {

    @RequiresApi(Build.VERSION_CODES.O)
    fun mapToUIModel(weatherForecastData: List<WeatherForecastData>): List<WeatherPredictionUIModel> {
        val result = weatherForecastData.map {
            WeatherPredictionUIModel(
                minTemp = it.main.temp_min.toDisplayString(),
                maxTemp = it.main.temp_max.toDisplayString(),
                dayOfWeek = it.date.toWeekDay(),
                icon = it.weather.single().icon.addIconToUrl()
            )
        }
        Log.d("Weather prediction UI Model", result.toString())
        return result
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun Long.toWeekDay(): String {
        return DateTimeFormatter.ofPattern("EEEE", Locale.getDefault())
            .format(LocalDateTime.ofInstant(Instant.ofEpochSecond(this), ZoneId.systemDefault()))
    }
}
