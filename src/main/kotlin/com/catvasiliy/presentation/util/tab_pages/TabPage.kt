package com.catvasiliy.presentation.util.tab_pages

import com.catvasiliy.presentation.client.client_details.ClientDetailsComponent
import com.catvasiliy.presentation.client.clients_list.ClientsListComponent
import com.catvasiliy.presentation.client.create_client.CreateClientComponent
import com.catvasiliy.presentation.repair_order.create_repair_order.CreateRepairOrderComponent
import com.catvasiliy.presentation.repair_order.repair_order_details.RepairOrderDetailsComponent
import com.catvasiliy.presentation.repair_order.repair_orders_list.RepairOrdersListComponent

sealed class TabPage(val tabPageTitle: String)

sealed class RepairOrderTabPage(tabPageTitle: String) : TabPage(tabPageTitle) {

    data class CreateRepairOrder(
        val component: CreateRepairOrderComponent
    ) : RepairOrderTabPage(tabPageTitle = "New Repair Order")

    data class RepairOrdersList(
        val component: RepairOrdersListComponent
    ) : RepairOrderTabPage(tabPageTitle = "Repair Orders")

    class RepairOrderDetails(
        repairOrderId: Int,
        val component: RepairOrderDetailsComponent
    ) : RepairOrderTabPage(tabPageTitle = "Repair Order #$repairOrderId")
}

sealed class ClientTabPage(tabPageTitle: String) : TabPage(tabPageTitle) {

    data class CreateClient(
        val component: CreateClientComponent
    ) : ClientTabPage(tabPageTitle = "New Client")

    data class ClientsList(
        val component: ClientsListComponent
    ) : ClientTabPage(tabPageTitle = "Clients")

    class ClientDetails(
        val component: ClientDetailsComponent,
        clientId: Int
    ) : ClientTabPage(tabPageTitle = "Client #$clientId")
}