package com.example.simpleapp.weather.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.simpleapp.R
import com.example.simpleapp.theme.SimpleAppTheme
import com.example.simpleapp.generic.ui.*
import com.example.simpleapp.theme.Spacing.x1
import com.example.simpleapp.theme.Spacing.x2
import com.example.simpleapp.weather.NavGraphs
import com.example.simpleapp.weather.destinations.MainScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.component.KoinComponent
import simpleapp.presentation.generic.UIState
import simpleapp.presentation.navigation.WeatherNavigationEvent
import simpleapp.presentation.weather.*

class MainActivity : ComponentActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleAppTheme {
                val navController = rememberNavController()

                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    startRoute = MainScreenDestination,
                    navController = navController
                )
            }
        }
    }

    companion object {

        private const val PREDICTION_INTENT_EXTRA = "PREDICTION_INTENT_EXTRA"
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}

@RootNavGraph(start = true)
@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navigator: DestinationsNavigator,
    viewModel: WeatherViewModel = koinViewModel(),
) {
    val weather by viewModel.weather.collectAsState()
    val date by viewModel.date.collectAsState()
    val state by viewModel.state.collectAsState()
    val city by viewModel.city.collectAsState()
    val navigation by viewModel.navigation.collectAsState(initial = null)
    val mainNavigator = remember(navigator) { MainNavigator(navigator) }

    LaunchedEffect(navigation) {
        navigation.let { event ->
            if (event == WeatherNavigationEvent.OpenWeatherPredictionScreen) {
                mainNavigator.navigateToWeatherPrediction(cityArgs = CityArgs(city))
            }
        }
    }

    Scaffold(
        topBar = { MainToolbar() },
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { paddingValues ->
        MainContent(
            onCurrentWeatherClick = { viewModel.getWeather(city) },
            inputField = {
                TextField(
                    value = city,
                    onValueChange = viewModel::setCity,
                    label = { Text(stringResource(R.string.input_label_textfield)) },
                    modifier = Modifier
                        .padding(vertical = x2)
                        .fillMaxWidth(),
                )
            },
            uiState = state,
            weatherInfoUIModel = weather,
            dateUIModel = date,
            onPredictionClick = { viewModel.navigateToWeatherPrediction() },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun MainContent(
    uiState: UIState,
    weatherInfoUIModel: WeatherInfoUIModel?,
    dateUIModel: DateUIModel?,
    onCurrentWeatherClick: () -> Unit,
    onPredictionClick: () -> Unit,
    inputField: @Composable (() -> Unit),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = x2, vertical = x2)
            .fillMaxWidth()
    ) {
        inputField()
        Button(
            onClick = { onCurrentWeatherClick() },
            modifier = Modifier
                .padding(bottom = x2)
                .fillMaxWidth(),
        ) {
            Text(text = stringResource(R.string.weather_today_button))
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            HandleState(
                uiState = uiState,
                weatherInfoUIModel = weatherInfoUIModel,
                dateUIModel = dateUIModel,
                onPredictionClick = onPredictionClick,
            )
        }
    }
}

@Composable
private fun HandleState(
    uiState: UIState,
    weatherInfoUIModel: WeatherInfoUIModel?,
    dateUIModel: DateUIModel?,
    onPredictionClick: () -> Unit,
) {
    when (uiState) {
        UIState.NORMAL -> NormalContent(
            weatherInfoUIModel = weatherInfoUIModel,
            dateUIModel = dateUIModel,
            onPredictionClick = onPredictionClick
        )
        UIState.LOADING -> CircularProgressIndicator()
        UIState.ERROR -> ErrorContent()
    }
}

@Composable
private fun NormalContent(
    weatherInfoUIModel: WeatherInfoUIModel?,
    dateUIModel: DateUIModel?,
    onPredictionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (weatherInfoUIModel != null) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            item {
                Text(
                    text = weatherInfoUIModel.name,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(bottom = x2)
                )
            }
            item {
                dateUIModel?.let {
                    Text(
                        text = DateUIMapper.mapDateUIModelToString(it),
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.padding(bottom = x2)
                    )
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1F)
                    ) {
                        Text(
                            text = weatherInfoUIModel.temperature,
                            style = MaterialTheme.typography.displayMedium,
                        )
                        Text(
                            text = stringResource(R.string.temperature_feels_like) + " " + weatherInfoUIModel.feelsLike,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = x1)
                        )
                    }
                    BoxWithConstraints(
                        modifier = Modifier.weight(1F)
                    ) {
                        val imageSize =
                            (maxHeight.times(0.5F)).coerceAtMost(maxWidth.times(0.5F))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = weatherInfoUIModel.icon),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(imageSize)
                            )
                            Body1(
                                text = weatherInfoUIModel.main,
                                modifier = Modifier.padding(bottom = x1)
                            )
                            Body1(
                                text = weatherInfoUIModel.description,
                                modifier = Modifier.padding(bottom = x1)
                            )
                        }
                    }
                }
            }
            item {
                Subtitle1(text = weatherInfoUIModel.maxTemp, modifier = Modifier)
                Body1StringRes(
                    stringResId = R.string.maximum_temp,
                    modifier = Modifier.padding(bottom = x1)
                )
            }
            item {
                Subtitle1(text = weatherInfoUIModel.minTemp, modifier = Modifier)
                Body1StringRes(
                    stringResId = R.string.min_temp,
                    modifier = Modifier.padding(bottom = x2)
                )
            }
            item {
                Button(onClick = { onPredictionClick() }) {
                    Text(text = stringResource(R.string.weather_forecast_button))
                }
            }
        }
    }
}
