package com.example.simpleapp.weather.prediction

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.simpleapp.R
import com.example.simpleapp.SimpleAppTheme
import com.example.simpleapp.generic.ui.AppToolbar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import simpleapp.presentation.weather.WeatherViewModel

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherPredictionScreen(
    destinationsNavigator: DestinationsNavigator,
    viewModel: WeatherViewModel = koinViewModel()
) {
    SimpleAppTheme {
        Scaffold(
            topBar = {
                AppToolbar(
                    textResId = R.string.weather_prediction_screen_toolbar,
                    textColor = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .clickable { destinationsNavigator.popBackStack() }
                        .background(MaterialTheme.colorScheme.primary)
                )
            },
            content = { WeatherPredictionContentScreen(Modifier.padding(it)) })
    }
}

@Composable
private fun WeatherPredictionContentScreen(modifier: Modifier = Modifier) {
}

@Preview
@Composable
private fun PreviewWeatherPredictionScreen() {
    SimpleAppTheme {
        WeatherPredictionScreen(EmptyDestinationsNavigator)
    }
}