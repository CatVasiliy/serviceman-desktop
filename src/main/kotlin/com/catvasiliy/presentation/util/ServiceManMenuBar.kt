package com.catvasiliy.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.MenuBarScope

@Composable
fun FrameWindowScope.ServiceManMenuBar(
    onNavigateToRepairOrdersList: () -> Unit,
    onNavigateToClientsList: () -> Unit
) {
    MenuBar {
        RepairOrderMenu(onNavigateToRepairOrdersList = onNavigateToRepairOrdersList)
        ClientMenu(onNavigateToClientsList = onNavigateToClientsList)
    }
}

@Composable
fun MenuBarScope.RepairOrderMenu(
    onNavigateToRepairOrdersList: () -> Unit
) {
    Menu(
        text = "Repair Order"
    ) {
        Item(
            text = "New Repair Order",
            onClick = {  }
        )
        Item(
            text = "Repair Orders List",
            onClick = onNavigateToRepairOrdersList
        )
    }
}

@Composable
fun MenuBarScope.ClientMenu(
    onNavigateToClientsList: () -> Unit
) {
    Menu(
        text = "Client"
    ) {
        Item(
            text = "New Client",
            onClick = {  }
        )
        Item(
            text = "Clients List",
            onClick = onNavigateToClientsList
        )
    }
}