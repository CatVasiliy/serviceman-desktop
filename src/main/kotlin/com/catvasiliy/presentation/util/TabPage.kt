package com.catvasiliy.presentation.util

import com.catvasiliy.presentation.client.client_details.ClientDetailsComponent
import com.catvasiliy.presentation.client.clients_list.ClientsListComponent
import com.catvasiliy.presentation.repair_order.repair_order_details.RepairOrderDetailsComponent
import com.catvasiliy.presentation.repair_order.repair_orders_list.RepairOrdersListComponent

sealed class TabPage(
    val tabPageType: TabPageType,
    val tabPageTitle: String
) {

    data class RepairOrdersList(
        val component: RepairOrdersListComponent,
    ) : TabPage(
        tabPageType = TabPageType.RepairOrdersList,
        tabPageTitle = "Repair Orders"
    )

    data class RepairOrderDetails(
        val repairOrderId: Int,
        val component: RepairOrderDetailsComponent
    ) : TabPage(
        tabPageType = TabPageType.RepairOrderDetails(repairOrderId),
        tabPageTitle = "Repair Order #$repairOrderId"
    )

    data class ClientsList(
        val component: ClientsListComponent,
    ) : TabPage(
        tabPageType = TabPageType.ClientsList,
        tabPageTitle = "Clients"
    )

    data class ClientDetails(
        val clientId: Int,
        val component: ClientDetailsComponent
    ) : TabPage(
        tabPageType = TabPageType.ClientDetails(clientId),
        tabPageTitle = "Client #$clientId"
    )
}