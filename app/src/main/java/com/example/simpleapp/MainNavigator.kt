package com.example.simpleapp

import com.example.simpleapp.destinations.WeatherPredictionScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import simpleapp.presentation.navigation.Navigator
import simpleapp.presentation.navigation.WeatherNavigationAction

class MainNavigator(private val navigator: DestinationsNavigator) : Navigator {

    override fun navigateToWeatherPredictionScreen(weatherNavigationAction: WeatherNavigationAction) {
        when(weatherNavigationAction) {
            WeatherNavigationAction.OPEN_WEATHER_PREDICTION -> {
                navigator.navigate(WeatherPredictionScreenDestination)
            }
        }
    }
}