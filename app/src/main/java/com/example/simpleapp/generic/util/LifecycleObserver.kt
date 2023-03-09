package com.example.simpleapp.generic.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun LifecycleObserver(
    lifecycleOwner: LifecycleOwner,
    lifecycleEvent: Lifecycle.Event,
    onEventReceived: () -> Unit
) {
    DisposableEffect(lifecycleOwner, lifecycleEvent) {
        val observer = object : LifecycleObserver {
            fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == lifecycleEvent) {
                    onEventReceived()
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}