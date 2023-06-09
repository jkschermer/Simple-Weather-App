package com.example.simpleapp.weather.current

import com.example.simpleapp.weather.destinations.WeatherPredictionScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import simpleapp.presentation.weather.CityArgs

class MainNavigator(private val navigator: DestinationsNavigator) {

    fun navigateToWeatherPrediction(cityArgs: CityArgs) {
        navigator.navigate(WeatherPredictionScreenDestination(cityArgs))
    }
}