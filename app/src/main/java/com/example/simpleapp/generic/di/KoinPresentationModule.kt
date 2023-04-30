package com.example.simpleapp.generic.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import simpleapp.presentation.prediction.WeatherPredictionViewModel
import simpleapp.presentation.weather.WeatherViewModel

val presentationModule = module {
    viewModel { WeatherViewModel(get(), get()) }
    viewModel { WeatherPredictionViewModel(get(), get()) }
}
