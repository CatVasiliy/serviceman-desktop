package com.catvasiliy.presentation.ui_components

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.MenuBarScope
import com.catvasiliy.presentation.util.tab_pages.ClientConfig
import com.catvasiliy.presentation.util.tab_pages.RepairOrderConfig
import com.catvasiliy.presentation.util.tab_pages.TabPageConfig

@Composable
fun FrameWindowScope.ServicemanMenuBar(
    onOpenNewTab: (TabPageConfig) -> Unit
) {
    MenuBar {
        RepairOrderMenu(
            onNavigateToCreateRepairOrder = { onOpenNewTab(RepairOrderConfig.CreateRepairOrder) },
            onNavigateToRepairOrdersList = { onOpenNewTab(RepairOrderConfig.RepairOrdersList) }
        )
        ClientMenu(
            onNavigateToCreateClient = { onOpenNewTab(ClientConfig.CreateClient) },
            onNavigateToClientsList = { onOpenNewTab(ClientConfig.ClientsList) }
        )
    }
}

@Composable
fun MenuBarScope.RepairOrderMenu(
    onNavigateToCreateRepairOrder: () -> Unit,
    onNavigateToRepairOrdersList: () -> Unit
) {
    Menu(
        text = "Repair Order"
    ) {
        Item(
            text = "New Repair Order",
            onClick = onNavigateToCreateRepairOrder
        )
        Item(
            text = "Repair Orders List",
            onClick = onNavigateToRepairOrdersList
        )
    }
}

@Composable
fun MenuBarScope.ClientMenu(
    onNavigateToCreateClient: () -> Unit,
    onNavigateToClientsList: () -> Unit
) {
    Menu(
        text = "Client"
    ) {
        Item(
            text = "New Client",
            onClick = onNavigateToCreateClient
        )
        Item(
            text = "Clients List",
            onClick = onNavigateToClientsList
        )
    }
}