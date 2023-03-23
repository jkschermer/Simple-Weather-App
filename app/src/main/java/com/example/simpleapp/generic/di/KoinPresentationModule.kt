package com.example.simpleapp.generic.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import simpleapp.presentation.prediction.WeatherPredictionViewModel
import simpleapp.presentation.weather.WeatherViewModel

val presentationModule = module {
    viewModel { WeatherViewModel(get(), get()) }
    viewModel { WeatherPredictionViewModel(get()) }
//    viewModel { IntroViewModel(get(), get()) }
//    viewModel { parameters -> ChangePasswordViewModel(intent = parameters.get(), get(), get(), get()) }
//    viewModel { parameters -> TransactionListViewModel(transactionCategory = parameters.get(), get(), get(), get()) }
//
//    factory { TransactionUIMapper(get()) }
//    factory { PortfolioOverviewUIMapper(get()) }
//    factory { ReturnUIMapper(get()) }
//
//    single { DateRangeSelectionState() }
//
//    factory<TransactionTextProvider> { ResourcesTransactionsTextProvider(get()) }
//    factory<PortfolioOverviewTextProvider> { ResourcesPortfolioOverviewTextProvider(get()) }
//
//    single<AnalyticsTracker> { FirebaseAnalyticsTracker(get()) }
//    factory { AppStateTracker(get()) }
}
