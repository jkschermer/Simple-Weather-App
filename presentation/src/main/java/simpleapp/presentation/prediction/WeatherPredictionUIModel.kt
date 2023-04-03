package simpleapp.presentation.prediction

data class WeatherPredictionUIModel(
    val minTemp: List<String>,
    val maxTemp: List<String>,
    val dayOfWeek: List<String>,
    val icon: List<String>
)
