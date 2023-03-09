package com.example.simpleapp.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.simpleapp.weather.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        startActivity(MainActivity.createIntent(this))
    }
}