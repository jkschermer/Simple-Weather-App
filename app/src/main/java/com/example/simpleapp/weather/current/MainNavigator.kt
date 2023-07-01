package com.example.simpleapp.weather.current

import com.example.simpleapp.weather.destinations.WeatherPredictionScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class MainNavigator(private val navigator: DestinationsNavigator) {

    fun navigateToWeatherPrediction(city: String) {
        navigator.navigate(WeatherPredictionScreenDestination(city.converseToCorrectFormat()))
    }

    private fun String.converseToCorrectFormat(): String {
        val regex = Regex("^\\p{Lu}\\p{Ll}*?\\s?(\\p{Lu}\\p{Ll}*)\$")

        if (this.matches(regex)) {
            return this
        }

        val result = this.split(" ").map { word ->
            word.lowercase().replaceFirstChar { it.uppercase() }
        }

        return result.joinToString(" ")
    }
}