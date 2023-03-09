package simpleapp.presentation.navigation

sealed class WeatherNavigationEvent {
    object OpenWeatherPredictionScreen : WeatherNavigationEvent()
    object NavigateBackToMain : WeatherNavigationEvent()
}
