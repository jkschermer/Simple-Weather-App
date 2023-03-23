package com.example.simpleapp.weather.prediction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.example.simpleapp.R
import com.example.simpleapp.SimpleAppTheme
import com.example.simpleapp.generic.ui.ErrorContent
import com.example.simpleapp.generic.ui.SecondaryToolbar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import simpleapp.presentation.generic.UIState
import simpleapp.presentation.navigation.WeatherNavigationEvent
import simpleapp.presentation.prediction.WeatherPredictionUIModel
import simpleapp.presentation.prediction.WeatherPredictionViewModel
import simpleapp.presentation.weather.CityArgs

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherPredictionScreen(
    navigator: DestinationsNavigator,
    cityArgs: CityArgs,
    viewModel: WeatherPredictionViewModel = koinViewModel()
) {
    val navigation by viewModel.navigation.collectAsState(initial = null)
    val predictionNavigator = remember(navigator) { PredictionNavigator(navigator) }
    val weatherPredictionUIModel by viewModel.weatherPrediction.collectAsState()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(navigation) {
        navigation.let { event ->
            if (event == WeatherNavigationEvent.NavigateBackToMain) {
                predictionNavigator.navigateBackToMain()
            }
        }
        cityArgs.city.let {
            viewModel.getWeatherPrediction(it)
        }
    }

    SimpleAppTheme {
        Scaffold(
            topBar = { SecondaryToolbar(onClick = viewModel::navigateBack) },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            MainContent(
                uiState = state,
                weatherPredictionUIModel = weatherPredictionUIModel,
                modifier = Modifier.padding(it)
            )
        }
    }
}

@Composable
private fun MainContent(
    uiState: UIState,
    weatherPredictionUIModel: WeatherPredictionUIModel?,
    modifier: Modifier = Modifier
) {
    HandleState(
        uiState = uiState,
        weatherPredictionUIModel = weatherPredictionUIModel,
        modifier = modifier
    )
}

@Composable
private fun HandleState(
    uiState: UIState,
    weatherPredictionUIModel: WeatherPredictionUIModel?,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        UIState.NORMAL -> weatherPredictionUIModel?.let {
            WeatherPredictionContentScreen(
                weatherPredictionUIModel = it,
                modifier = modifier
            )
        }
        UIState.LOADING -> CircularProgressIndicator()
        UIState.ERROR -> ErrorContent()
    }
}

@Composable
private fun WeatherPredictionContentScreen(
    weatherPredictionUIModel: WeatherPredictionUIModel,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        item {
            Text(stringResource(R.string.weather_prediction_subtitle_text))
        }
        item {
            Column {
                Text(weatherPredictionUIModel.minTemp)
                Text(weatherPredictionUIModel.maxTemp)
                Image(
                    painter = rememberAsyncImagePainter(weatherPredictionUIModel.icon),
                    contentDescription = null
                )
            }
        }
    }
}
