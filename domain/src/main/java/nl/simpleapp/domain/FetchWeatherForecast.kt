package nl.simpleapp.domain


import nl.simpleapp.domain.weather.data.WeatherForecastRepository
import nl.simpleapp.domain.weather.model.Main
import nl.simpleapp.domain.weather.model.Weather
import nl.simpleapp.domain.weather.model.WeatherForecastData

class FetchWeatherForecast(private val weatherForecastRepository: WeatherForecastRepository) {

    suspend operator fun invoke(city: String): List<WeatherForecastData> {
        val weatherForecast = weatherForecastRepository.fetchWeatherForecast(city)
        val forecastList = weatherForecast.map { it.toCelsius() }
        val result = mutableListOf<WeatherForecastData>()
        var startIndex = 0

        while (startIndex < forecastList.size) {
            val endIndex = (startIndex + 8).coerceAtMost(forecastList.size)
            var weatherForecastData: WeatherForecastData? = null
            val subList = forecastList.subList(startIndex, endIndex)
            val maxTemp = subList.maxOfOrNull { it.main.temp_max } ?: 0.0
            val minTemp = subList.minOfOrNull { it.main.temp_min } ?: 0.0
            val frequencyMap: HashMap<String, Int> = HashMap()
            val icons = subList.flatMap { forecastData -> forecastData.weather.map { it.icon } }
            val icon = selectMostCommonWeatherIcon(icons, frequencyMap)

            icon?.let { ic ->
                subList.map { it ->
                    weatherForecastData = WeatherForecastData(
                        date = it.date, main = Main(
                            temp = it.main.temp,
                            feels_like = it.main.feels_like,
                            temp_min = minTemp,
                            temp_max = maxTemp,
                            pressure = it.main.pressure,
                            humidity = it.main.humidity,
                        ), weather = it.weather.map {
                            Weather(
                                id = it.id,
                                main = it.main,
                                description = it.description,
                                icon = ic
                            )
                        }, wind = it.wind
                    )
                }
            }
            weatherForecastData?.let { result.add(it) }
            startIndex = endIndex
        }
        return result
    }
}

private fun selectMostCommonWeatherIcon(icons: List<String>, frequencyMap: HashMap<String, Int>): String? {
    var icon: String? = null
    var max = 0

    for (value in icons) {
        frequencyMap[value] = frequencyMap.getOrDefault(value, 0) + 1
    }

    for (value in frequencyMap.keys) {
        val frequency = frequencyMap[value]
        if (frequency != null) {
            if (frequency > max) {
                max = frequency
                icon = value
            }
        }
    }
    return icon
}
