package simpleapp.presentation.weather.prediction.model

data class WeatherPredictionUIModel(
    val minTemp: List<String>,
    val maxTemp: List<String>,
    val dayOfWeek: List<String>,
    val icon: List<String>
)
