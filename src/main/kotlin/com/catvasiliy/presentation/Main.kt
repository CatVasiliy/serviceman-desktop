package com.catvasiliy.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.*
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.catvasiliy.data.ClientRepositoryImpl
import com.catvasiliy.data.RepairOrderRepositoryImpl
import com.catvasiliy.di.appModule
import com.catvasiliy.presentation.client.client_details.ClientDetailsTab
import com.catvasiliy.presentation.client.clients_list.ClientsListTab
import com.catvasiliy.presentation.repair_order.repair_order_details.RepairOrderDetailsTab
import com.catvasiliy.presentation.repair_order.repair_orders_list.RepairOrdersListTab
import com.catvasiliy.presentation.util.ServiceManMenuBar
import com.catvasiliy.presentation.util.TabPage
import com.catvasiliy.presentation.util.TabPageType
import com.catvasiliy.presentation.util.TabPages
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.context.startKoin
import javax.swing.SwingUtilities

@OptIn(ExperimentalDecomposeApi::class)
fun main() {

    startKoin {
        modules(appModule)
    }

    val httpClient = HttpClient(CIO) {
        defaultRequest {
            url("http://localhost:8080/")
        }
        install(ContentNegotiation) {
            json()
        }
        install(Logging)
    }

    val repairOrderRepository = RepairOrderRepositoryImpl(httpClient)
    val clientRepository = ClientRepositoryImpl(httpClient)

    val lifecycle = LifecycleRegistry()

    val rootComponent = runOnUiThread {
        RootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
            repairOrderRepository = repairOrderRepository,
            clientRepository = clientRepository
        )
    }

    application {
        val windowState = rememberWindowState()

        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            title = "ServiceMan"
        ) {
            ServiceManMenuBar(
                onNavigateToRepairOrdersList = { rootComponent.newTabPage(TabPageType.RepairOrdersList) },
                onNavigateToClientsList = { rootComponent.newTabPage(TabPageType.ClientsList) }
            )
            MaterialTheme {
                Surface {
                    Column {
                        TabPages(
                            tabPagesValue = rootComponent.tabPages,
                            onSelect = { rootComponent.selectTabPage(it) },
                            onClose = { rootComponent.closeTabPage(it) },
                            modifier = Modifier.fillMaxSize()
                        ) { tab ->
                            when(tab) {
                                is TabPage.RepairOrdersList -> RepairOrdersListTab(tab.component)
                                is TabPage.RepairOrderDetails -> RepairOrderDetailsTab(tab.component)
                                is TabPage.ClientsList -> ClientsListTab(tab.component)
                                is TabPage.ClientDetails -> ClientDetailsTab(tab.component)
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun <T> runOnUiThread(block: () -> T): T {
    if (SwingUtilities.isEventDispatchThread()) {
        return block()
    }

    var error: Throwable? = null
    var result: T? = null

    SwingUtilities.invokeAndWait {
        try {
            result = block()
        } catch (e: Throwable) {
            error = e
        }
    }

    error?.also { throw it }

    @Suppress("UNCHECKED_CAST")
    return result as T
}