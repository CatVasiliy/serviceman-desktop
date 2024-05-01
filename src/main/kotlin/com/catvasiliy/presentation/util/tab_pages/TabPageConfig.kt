package com.catvasiliy.presentation.util.tab_pages

import kotlinx.serialization.Serializable

@Serializable
sealed interface TabPageConfig

@Serializable
sealed interface RepairOrderConfig : TabPageConfig {

    @Serializable
    data object CreateRepairOrder : RepairOrderConfig

    @Serializable
    data object RepairOrdersList : RepairOrderConfig

    @Serializable
    data class RepairOrderDetails(val repairOrderId: Int) : RepairOrderConfig
}

@Serializable
sealed interface ClientConfig : TabPageConfig {

    @Serializable
    data object CreateClient : ClientConfig

    @Serializable
    data object ClientsList : ClientConfig

    @Serializable
    data class ClientDetails(val clientId: Int) : ClientConfig
}