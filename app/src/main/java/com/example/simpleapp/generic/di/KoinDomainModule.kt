package com.example.simpleapp.generic.di

import nl.simpleapp.domain.weather.current.FetchWeather
import nl.simpleapp.domain.weather.forecast.FetchWeatherForecast
import nl.simpleapp.domain.city.FetchImage
import nl.simpleapp.domain.time.FetchDate
import org.koin.dsl.module

val domainModule = module {
    factory { FetchWeather(get()) }
    factory { FetchDate(get()) }
    factory { FetchWeatherForecast(get()) }
    factory { FetchImage(get()) }
}
