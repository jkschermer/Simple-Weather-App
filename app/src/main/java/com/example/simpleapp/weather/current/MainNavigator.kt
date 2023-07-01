package com.example.simpleapp.weather.current

import com.example.simpleapp.weather.destinations.WeatherPredictionScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class MainNavigator(private val navigator: DestinationsNavigator) {

    fun navigateToWeatherPrediction(city: String) {
        navigator.navigate(WeatherPredictionScreenDestination(city.converseToCorrectFormat()))
    }

    private fun String.converseToCorrectFormat(): String {
        val regex = Regex("^[A-Z]\\S*(?:\\s+[A-Z]\\S*)*$")

        if (this.matches(regex)) {
            return this
        }

        val result = this.split(" ").map { word ->
            word.lowercase().replaceFirstChar { it.uppercase() }
        }

        return result.joinToString(" ")
    }
}