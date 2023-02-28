package com.example.simpleapp.generic.di

import nl.simpleapp.domain.time.data.DateRepository
import nl.simpleapp.domain.weather.data.ReturnWeatherRepository
import org.koin.dsl.module
import simpleapp.data.date.DateDataSource
import simpleapp.data.date.DateRepositoryImpl
import simpleapp.data.date.LocalDateDataSource
import simpleapp.data.generic.network.HttpClients
import simpleapp.data.weather.network.RemoteReturnWeatherRepository
import simpleapp.data.weather.network.ReturnWeatherService

val dataModule = module {
    single<ReturnWeatherRepository> { RemoteReturnWeatherRepository(get()) }
    single { ReturnWeatherService(get()) }
    single<DateRepository> { DateRepositoryImpl(get()) }
    single<DateDataSource> { LocalDateDataSource() }
    single { HttpClients() }
}
