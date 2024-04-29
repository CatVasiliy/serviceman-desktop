package com.catvasiliy.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.MenuBarScope

@Composable
fun FrameWindowScope.ServiceManMenuBar(
    onNavigateToCreateRepairOrder: () -> Unit,
    onNavigateToRepairOrdersList: () -> Unit,
    onNavigateToCreateClient: () -> Unit,
    onNavigateToClientsList: () -> Unit
) {
    MenuBar {
        RepairOrderMenu(
            onNavigateToCreateRepairOrder = onNavigateToCreateRepairOrder,
            onNavigateToRepairOrdersList = onNavigateToRepairOrdersList
        )
        ClientMenu(
            onNavigateToCreateClient = onNavigateToCreateClient,
            onNavigateToClientsList = onNavigateToClientsList
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