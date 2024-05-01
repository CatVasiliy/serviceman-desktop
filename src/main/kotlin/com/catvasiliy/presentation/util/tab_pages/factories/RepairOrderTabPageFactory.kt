package com.catvasiliy.presentation.util.tab_pages.factories

import com.arkivanov.decompose.ComponentContext
import com.catvasiliy.domain.repository.RepairOrderRepository
import com.catvasiliy.presentation.repair_order.create_repair_order.CreateRepairOrderComponent
import com.catvasiliy.presentation.repair_order.repair_order_details.RepairOrderDetailsComponent
import com.catvasiliy.presentation.repair_order.repair_orders_list.RepairOrdersListComponent
import com.catvasiliy.presentation.util.tab_pages.RepairOrderConfig
import com.catvasiliy.presentation.util.tab_pages.RepairOrderTabPage
import com.catvasiliy.presentation.util.tab_pages.TabPageConfig

class RepairOrderTabPageFactory(
    private val repository: RepairOrderRepository
) {
    fun createTabPage(
        tabPageConfig: RepairOrderConfig,
        componentContext: ComponentContext,
        onNavigate: (TabPageConfig) -> Unit
    ): RepairOrderTabPage {
        return when (tabPageConfig) {
            is RepairOrderConfig.CreateRepairOrder -> RepairOrderTabPage.CreateRepairOrder(
                component = CreateRepairOrderComponent(
                    componentContext = componentContext,
                    repository = repository
                )
            )
            is RepairOrderConfig.RepairOrdersList -> RepairOrderTabPage.RepairOrdersList(
                component = RepairOrdersListComponent(
                    componentContext = componentContext,
                    repository = repository,
                    onNavigateToDetails = { onNavigate(RepairOrderConfig.RepairOrderDetails(repairOrderId = it)) }
                )
            )
            is RepairOrderConfig.RepairOrderDetails -> RepairOrderTabPage.RepairOrderDetails(
                component = RepairOrderDetailsComponent(
                    componentContext = componentContext,
                    repository = repository,
                    repairOrderId = tabPageConfig.repairOrderId
                ),
                repairOrderId = tabPageConfig.repairOrderId
            )
        }
    }
}