package com.example.simpleapp

import android.app.Application
import android.util.Log
import com.example.simpleapp.generic.di.appModule
import com.example.simpleapp.generic.di.dataModule
import com.example.simpleapp.generic.di.domainModule
import com.example.simpleapp.generic.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class MainApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin()
        Log.i(null, "The simple app is created")
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(appModule, dataModule, presentationModule, domainModule))
        }
    }
}
