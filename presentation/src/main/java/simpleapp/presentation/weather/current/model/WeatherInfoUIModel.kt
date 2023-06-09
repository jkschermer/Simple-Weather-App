package simpleapp.presentation.weather.current.model

data class WeatherInfoUIModel(
    val temperature: String,
    val feelsLike: String,
    val minTemp: String,
    val maxTemp: String,
    val humidity: String,
    val wind: String,
    val main: String,
    val description: String,
    val icon: String,
    val name: String,
)