package com.catvasiliy.presentation.repair_order

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.catvasiliy.presentation.ExtendedTab
import com.catvasiliy.presentation.repair_order.repair_orders_list.RepairOrdersListScreen

class RepairOrderTab(index: Int) : ExtendedTab(index, "Repair Order") {

    @Composable
    override fun Content() {
        Navigator(RepairOrdersListScreen())
    }
}