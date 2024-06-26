package com.catvasiliy.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.catvasiliy.di.appModule
import com.catvasiliy.presentation.client.ClientTab
import com.catvasiliy.presentation.repair_order.RepairOrderTab
import com.catvasiliy.presentation.ui_components.ServicemanMenuBar
import com.catvasiliy.presentation.ui_components.TabPages
import com.catvasiliy.presentation.util.runOnUiThread
import com.catvasiliy.presentation.util.tab_pages.ClientTabPage
import com.catvasiliy.presentation.util.tab_pages.RepairOrderTabPage
import org.koin.core.context.startKoin

@OptIn(ExperimentalDecomposeApi::class)
fun main() {

    startKoin {
        modules(appModule)
    }

    val lifecycle = LifecycleRegistry()

    val mainComponent = runOnUiThread {
        MainComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle)
        )
    }

    application {
        val windowState = rememberWindowState()

        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            title = "Serviceman"
        ) {
            MaterialTheme {
                Surface {
                    ServicemanMenuBar(onOpenNewTab = { mainComponent.newTabPage(newTabPageConfig = it) } )
                    Column {
                        TabPages(
                            tabPagesValue = mainComponent.tabPages,
                            onSelect = mainComponent::selectTabPage,
                            onClose = mainComponent::closeTabPage,
                            modifier = Modifier.fillMaxSize()
                        ) { tabPage ->
                            when(tabPage) {
                                is RepairOrderTabPage -> RepairOrderTab(tabPage)
                                is ClientTabPage -> ClientTab(tabPage)
                            }
                        }
                    }
                }
            }
        }
    }
}