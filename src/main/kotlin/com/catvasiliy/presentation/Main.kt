package com.catvasiliy.presentation

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.catvasiliy.di.appModule
import org.koin.core.context.startKoin

fun main() = application {

    startKoin {
        modules(appModule)
    }

    Window(onCloseRequest = ::exitApplication) {
        MainScreen().Content()
    }
}