package simpleapp.presentation.navigation

import nl.simpleapp.domain.weather.model.Wind

sealed class WeatherNavigationAction {

    data class OpenDetailWind(
        val wind: Wind
    ) : WeatherNavigationAction()

    object OpenHome : WeatherNavigationAction()
}