package com.example.simpleapp.generic.di

import nl.simpleapp.domain.time.data.DateRepository
import nl.simpleapp.domain.weather.data.WeatherRepository
import org.koin.dsl.module
import simpleapp.data.date.DateDataSource
import simpleapp.data.date.DateRepositoryImpl
import simpleapp.data.date.LocalDateDataSource
import simpleapp.data.generic.network.HttpClients
import simpleapp.data.weather.network.RemoteWeatherRepository
import simpleapp.data.weather.network.WeatherService

val dataModule = module {
    single<WeatherRepository> { RemoteWeatherRepository(get()) }
    single { WeatherService(get()) }
    single<DateRepository> { DateRepositoryImpl(get()) }
    single<DateDataSource> { LocalDateDataSource() }
    single { HttpClients() }
}
