package com.catvasiliy.presentation.repair_order

import androidx.compose.runtime.Composable
import com.catvasiliy.presentation.repair_order.create_repair_order.CreateRepairOrderTab
import com.catvasiliy.presentation.repair_order.repair_order_details.RepairOrderDetailsTab
import com.catvasiliy.presentation.repair_order.repair_orders_list.RepairOrdersListTab
import com.catvasiliy.presentation.util.tab_pages.RepairOrderTabPage

@Composable
fun RepairOrderTab(
    tabPage: RepairOrderTabPage
) {
    when(tabPage) {
        is RepairOrderTabPage.CreateRepairOrder -> CreateRepairOrderTab(tabPage.component)
        is RepairOrderTabPage.RepairOrdersList -> RepairOrdersListTab(tabPage.component)
        is RepairOrderTabPage.RepairOrderDetails -> RepairOrderDetailsTab(tabPage.component)
    }
}