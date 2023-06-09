package com.example.simpleapp.generic.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import simpleapp.presentation.weather.prediction.WeatherPredictionViewModel
import simpleapp.presentation.weather.current.WeatherViewModel

val presentationModule = module {
    viewModel { WeatherViewModel(get(), get()) }
    viewModel { WeatherPredictionViewModel(get(), get()) }
}
