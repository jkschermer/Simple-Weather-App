package com.example.simpleapp.weather.prediction

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.simpleapp.R
import com.example.simpleapp.generic.ui.Body1
import com.example.simpleapp.generic.ui.Body1StringRes
import com.example.simpleapp.generic.ui.ErrorContent
import com.example.simpleapp.generic.ui.SecondaryToolbar
import com.example.simpleapp.theme.Spacing.x1
import com.example.simpleapp.theme.Spacing.x2
import com.example.simpleapp.theme.Spacing.x3
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.ktor.util.reflect.*
import org.koin.androidx.compose.koinViewModel
import simpleapp.presentation.generic.UIState
import simpleapp.presentation.image.ImageUIModel
import simpleapp.presentation.navigation.WeatherNavigationEvent
import simpleapp.presentation.weather.prediction.model.WeatherPredictionUIModel
import simpleapp.presentation.weather.prediction.WeatherPredictionViewModel
import simpleapp.presentation.weather.CityArgs

val ICON_SIZE = Size(50.dp.value, 50.dp.value)

@RequiresApi(Build.VERSION_CODES.O)
@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherPredictionScreen(
    navigator: DestinationsNavigator,
    cityArgs: CityArgs,
    viewModel: WeatherPredictionViewModel = koinViewModel(),
) {
    val navigation by viewModel.navigation.collectAsState(initial = null)
    val predictionNavigator = remember(navigator) { PredictionNavigator(navigator) }
    val weatherPredictions by viewModel.weatherPrediction.collectAsState()
    val imageUIModel by viewModel.imageUIModel.collectAsState()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(navigation) {
        navigation.let {
            if (it == WeatherNavigationEvent.NavigateBackToMain) {
                predictionNavigator.navigateBackToMain()
            } else {
                cityArgs.city.let(viewModel::getWeatherPrediction)
                viewModel.getCityImage(cityArgs.city)
            }
        }
    }

    Scaffold(
        topBar = { SecondaryToolbar(text = cityArgs.city, onClick = viewModel::navigateBack) },
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        MainContent(
            uiState = state,
            weatherPredictionUIModel = weatherPredictions,
            imageUIModel = imageUIModel,
            cityArgs = cityArgs,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
private fun MainContent(
    uiState: UIState,
    weatherPredictionUIModel: List<WeatherPredictionUIModel>,
    imageUIModel: ImageUIModel?,
    cityArgs: CityArgs,
    modifier: Modifier = Modifier
) {
    HandleState(
        uiState = uiState,
        weatherPredictionUIModel = weatherPredictionUIModel,
        imageUIModel = imageUIModel,
        cityArgs = cityArgs,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = x2, vertical = x2)
    )
}

@Composable
private fun HandleState(
    uiState: UIState,
    weatherPredictionUIModel: List<WeatherPredictionUIModel>,
    imageUIModel: ImageUIModel?,
    cityArgs: CityArgs,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        UIState.NORMAL -> {
            WeatherPredictionContentScreen(
                weatherPredictionUIModel = weatherPredictionUIModel,
                cityArgs = cityArgs,
                imageUIModel = imageUIModel,
                modifier = modifier
            )
        }
        UIState.LOADING -> CircularProgressIndicator(modifier = modifier.wrapContentSize())
        UIState.ERROR -> ErrorContent()
    }
}

@Composable
private fun WeatherPredictionContentScreen(
    weatherPredictionUIModel: List<WeatherPredictionUIModel>,
    imageUIModel: ImageUIModel?,
    cityArgs: CityArgs,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = x2),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Text(
                text = cityArgs.city,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(vertical = x1)
            )
        }
        item {
            Body1StringRes(
                R.string.weather_prediction_subtitle_text,
                modifier = Modifier.padding(bottom = x2)
            )
        }
        itemsIndexed(weatherPredictionUIModel) { index, weather ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .padding(start = x2)
                        .fillMaxWidth()
                ) {
                    Body1(
                        weather.minTemp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(0.65F)
                    )
                    Body1(
                        weather.maxTemp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(1F)
                    )
                    Body1(
                        weather.dayOfWeek,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(1F)
                    )
                    Image(
                        painter = rememberAsyncImagePainter(weather.icon),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(ICON_SIZE.width.dp, ICON_SIZE.height.dp),
                        contentDescription = null,
                    )
                }
            }
        }
        item {
            DisplayCityImage(imageUIModel = imageUIModel)
        }
    }
}

@Composable
private fun DisplayCityImage(
    imageUIModel: ImageUIModel?,
    modifier: Modifier = Modifier
) {
    imageUIModel.let {
        if (it != null) {
            Image(
                painter = rememberAsyncImagePainter(model = it.imageUrl),
                contentDescription = "city",
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .size(200.dp)
                    .aspectRatio(1F)
                    .padding(vertical = x3)
            )
        } else {
            CircularProgressIndicator(modifier = modifier.wrapContentSize())
        }
    }
}
