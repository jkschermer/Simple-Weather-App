package com.example.simpleapp.weather.prediction

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class PredictionNavigator(private val navigator: DestinationsNavigator) {

    fun navigateBackToMain() {
        navigator.popBackStack()
    }
}