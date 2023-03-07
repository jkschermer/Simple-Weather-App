package com.example.simpleapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.simpleapp.generic.ui.AppToolbar
import com.example.simpleapp.ui.theme.SimpleAppTheme
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherPredictionScreen() {
    SimpleAppTheme {
        Scaffold(
            topBar = {
                AppToolbar(
                    textResId = R.string.weather_prediction_screen_toolbar,
                    textColor = MaterialTheme.colorScheme.primary
                )
            },
            content = { WeatherPredictionContentScreen(Modifier.padding(it))})
    }
}

@Composable
private fun WeatherPredictionContentScreen(modifier: Modifier = Modifier) {

}