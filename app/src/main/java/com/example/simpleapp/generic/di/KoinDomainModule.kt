package com.example.simpleapp.generic.di

import nl.simpleapp.domain.FetchWeather
import nl.simpleapp.domain.time.FetchDate
import org.koin.dsl.module

val domainModule = module {
    factory { FetchWeather(get()) }
    factory { FetchDate(get()) }
}
