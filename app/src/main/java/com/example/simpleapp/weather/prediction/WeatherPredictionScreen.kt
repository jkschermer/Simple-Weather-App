package com.example.simpleapp.weather.prediction

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.simpleapp.R
import com.example.simpleapp.generic.ui.Body1
import com.example.simpleapp.generic.ui.Body1StringRes
import com.example.simpleapp.generic.ui.ErrorContent
import com.example.simpleapp.generic.ui.SecondaryToolbar
import com.example.simpleapp.theme.Spacing.x2
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import simpleapp.presentation.generic.UIState
import simpleapp.presentation.navigation.WeatherNavigationEvent
import simpleapp.presentation.prediction.WeatherPredictionUIModel
import simpleapp.presentation.prediction.WeatherPredictionViewModel
import simpleapp.presentation.weather.CityArgs

val ICON_SIZE =  Size(50.dp.value, 50.dp.value)

@RequiresApi(Build.VERSION_CODES.O)
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
        navigation.let {
            if (it == WeatherNavigationEvent.NavigateBackToMain) {
                predictionNavigator.navigateBackToMain()
            } else {
                cityArgs.city.let(viewModel::getWeatherPrediction)
            }
        }
    }

    Scaffold(
        topBar = { SecondaryToolbar(onClick = viewModel::navigateBack) },
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        MainContent(
            uiState = state,
            weatherPredictionUIModel = weatherPredictionUIModel,
            cityArgs = cityArgs,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
private fun MainContent(
    uiState: UIState,
    weatherPredictionUIModel: WeatherPredictionUIModel?,
    cityArgs: CityArgs,
    modifier: Modifier = Modifier
) {
    HandleState(
        uiState = uiState,
        weatherPredictionUIModel = weatherPredictionUIModel,
        cityArgs = cityArgs,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = x2, vertical = x2)
    )
}

@Composable
private fun HandleState(
    uiState: UIState,
    weatherPredictionUIModel: WeatherPredictionUIModel?,
    cityArgs: CityArgs,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        UIState.NORMAL -> {
            weatherPredictionUIModel?.let {
                WeatherPredictionContentScreen(
                    weatherPredictionUIModel = it,
                    cityArgs = cityArgs,
                    modifier = modifier
                )
            }
        }
        UIState.LOADING -> CircularProgressIndicator(modifier = modifier.wrapContentSize())
        UIState.ERROR -> ErrorContent()
    }
}

@Composable
private fun WeatherPredictionContentScreen(
    weatherPredictionUIModel: WeatherPredictionUIModel,
    cityArgs: CityArgs,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = x2),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item { Body1StringRes(R.string.weather_prediction_subtitle_text) }
        item { Body1(cityArgs.city) }
        items(weatherPredictionUIModel.icon.size) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Body1(weatherPredictionUIModel.minTemp[it])
                    Body1(weatherPredictionUIModel.maxTemp[it])
                    Body1(weatherPredictionUIModel.dayOfWeek[it])
                    Image(
                        painter = rememberAsyncImagePainter(weatherPredictionUIModel.icon[it]),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(ICON_SIZE.width.dp, ICON_SIZE.height.dp),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}
