package com.example.simpleapp.weather.prediction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.example.simpleapp.R
import com.example.simpleapp.SimpleAppTheme
import com.example.simpleapp.generic.ui.SecondaryToolbar
import com.example.simpleapp.theme.Spacing
import com.example.simpleapp.weather.destinations.MainScreenDestination
import com.example.simpleapp.weather.destinations.WeatherPredictionScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import simpleapp.presentation.navigation.WeatherNavigationEvent
import simpleapp.presentation.weather.WeatherPredictionUIModel
import simpleapp.presentation.weather.WeatherViewModel

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherPredictionScreen(
    navigator: DestinationsNavigator,
    viewModel: WeatherViewModel = koinViewModel()
) {
    val weatherPrediction = viewModel.weatherPrediction.collectAsState()
    val navigation = viewModel.navigation.collectAsState(initial = null)

    LaunchedEffect(navigation.value) {
        navigation.value.let { event ->
            if (event == WeatherNavigationEvent.NavigateBackToMain) {
                navigator.popBackStack()
            }
        }
    }

    SimpleAppTheme {
        Scaffold(
            topBar = { SecondaryToolbar(onClick = { viewModel.navigateBack() }) }
        ) {
            WeatherPredictionContentScreen(
                weatherPredictionUIModel = weatherPrediction.value,
                weatherPredictionOnClick = { viewModel.getWeatherPrediction("Amsterdam") },
                modifier = Modifier.padding(it)
            )
        }
    }
}

@Composable
private fun WeatherPredictionContentScreen(
    weatherPredictionUIModel: WeatherPredictionUIModel?,
    weatherPredictionOnClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = { weatherPredictionOnClick() },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .padding(bottom = Spacing.x2)
            .fillMaxWidth(),
    ) {
        Text(text = stringResource(R.string.check_weather_prediction))
    }
    if (weatherPredictionUIModel != null) {
        LazyColumn(modifier = modifier) {
            item {
                Text(text = stringResource(R.string.weather_prediction_subtitle_text))
            }
            item {
                Column {
                    Text(text = weatherPredictionUIModel.minTemp)
                    Text(text = weatherPredictionUIModel.maxTemp)
                    Image(
                        painter = rememberAsyncImagePainter(model = weatherPredictionUIModel.icon),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewWeatherPredictionScreen() {
    SimpleAppTheme {
        WeatherPredictionScreen(EmptyDestinationsNavigator)
    }
}