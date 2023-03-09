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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.simpleapp.R
import com.example.simpleapp.SimpleAppTheme
import com.example.simpleapp.generic.ui.AppToolbar
import com.example.simpleapp.theme.Spacing.x1
import com.example.simpleapp.theme.Spacing.x2
import com.example.simpleapp.weather.NavGraphs
import com.example.simpleapp.weather.destinations.MainScreenDestination
import com.example.simpleapp.weather.destinations.WeatherPredictionScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.component.KoinComponent
import simpleapp.presentation.generic.UIState
import simpleapp.presentation.weather.DateUIMapper
import simpleapp.presentation.weather.DateUIModel
import simpleapp.presentation.weather.WeatherInfoUIModel
import simpleapp.presentation.weather.WeatherViewModel

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
    destinationsNavigator: DestinationsNavigator,
    viewModel: WeatherViewModel = koinViewModel()
) {
    val weather = viewModel.weather.collectAsState()
    val date = viewModel.date.collectAsState()
    val state = viewModel.state.collectAsState()
    var city: String by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            AppToolbar(
                textResId = R.string.homescreen_toolbar_title,
                textColor = MaterialTheme.colorScheme.background,
                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            )
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) { padding ->
        MainContent(
            onWeatherClick = { viewModel.getWeather(city) },
            InputField = { modifier ->
                TextField(
                    value = city,
                    onValueChange = { city = it },
                    label = { Text(stringResource(R.string.input_label_textfield)) },
                    modifier = modifier,
                )
            },
            uiState = state.value,
            weatherInfoUIModel = weather.value,
            dateUIModel = date.value,
            onPredictionClick = { destinationsNavigator.navigate(WeatherPredictionScreenDestination) },
            modifier = Modifier.padding(paddingValues = padding)
        )
    }
}

@Composable
fun MainContent(
    onWeatherClick: () -> Unit,
    InputField: @Composable ((Modifier) -> Unit),
    uiState: UIState,
    weatherInfoUIModel: WeatherInfoUIModel?,
    dateUIModel: DateUIModel?,
    onPredictionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = x2, vertical = x2)
            .fillMaxWidth()
    ) {
        InputField(
            Modifier
                .padding(vertical = x2)
                .fillMaxWidth()
        )
        Button(
            onClick = { onWeatherClick() },
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
            onPredictionClick = onPredictionClick,
        )
        UIState.LOADING -> LoadingContent()
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
                            modifier = Modifier
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
                        val imageSize = (maxHeight.times(0.5F)).coerceAtMost(maxWidth.times(0.5F))
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
                            Text(
                                text = weatherInfoUIModel.main,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = x1)
                            )
                            Text(
                                text = weatherInfoUIModel.description,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = x1)
                            )
                        }
                    }
                }
            }
            item {
                Text(
                    text = weatherInfoUIModel.maxTemp,
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                )
                Text(
                    text = stringResource(R.string.maximum_temp),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = x1)
                )
            }
            item {
                Text(
                    text = weatherInfoUIModel.minTemp,
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                )
                Text(
                    text = stringResource(R.string.maximum_temp),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = x2)
                )
            }
            item {
                Button(onClick = { onPredictionClick() }) {
                    Text(text = stringResource(R.string.weather_forecast_button))
                }
            }
        }
    } else {
        Text(stringResource(R.string.empty_string))
    }
}


@Composable
private fun LoadingContent() {
    CircularProgressIndicator()
}

@Composable
private fun ErrorContent() {
    Text(stringResource(R.string.main_error_message))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleAppTheme {
        MainContent({}, {}, UIState.NORMAL, WeatherInfoUIModel(
            "10.2",
            "10.2",
            "10.2",
            "10.2",
            "10.2",
            "Clouds",
            "Clouds",
            "https://people.sc.fsu.edu/~jburkardt/data/png/ajou_logo.png",
            "Amsterdam"
        ), dateUIModel = DateUIModel("10", "10", "2023", "wednesday"), {}
        )
    }
}

