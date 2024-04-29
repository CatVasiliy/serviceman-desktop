package com.catvasiliy.presentation.util

import com.catvasiliy.presentation.client.client_details.ClientDetailsComponent
import com.catvasiliy.presentation.client.clients_list.ClientsListComponent
import com.catvasiliy.presentation.client.create_client.CreateClientComponent
import com.catvasiliy.presentation.repair_order.create_repair_order.CreateRepairOrderComponent
import com.catvasiliy.presentation.repair_order.repair_order_details.RepairOrderDetailsComponent
import com.catvasiliy.presentation.repair_order.repair_orders_list.RepairOrdersListComponent

sealed class TabPage(
    val tabPageTitle: String
) {
    data class CreateRepairOrder(
        val component: CreateRepairOrderComponent
    ) : TabPage(tabPageTitle = "New Repair Order")

    data class RepairOrdersList(
        val component: RepairOrdersListComponent
    ) : TabPage(tabPageTitle = "Repair Orders")

    class RepairOrderDetails(
        repairOrderId: Int,
        val component: RepairOrderDetailsComponent
    ) : TabPage(tabPageTitle = "Repair Order #$repairOrderId")

    data class CreateClient(
        val component: CreateClientComponent
    ) : TabPage(tabPageTitle = "New Client")

    data class ClientsList(
        val component: ClientsListComponent
    ) : TabPage(tabPageTitle = "Clients")

    class ClientDetails(
        clientId: Int,
        val component: ClientDetailsComponent
    ) : TabPage(tabPageTitle = "Client #$clientId")
}