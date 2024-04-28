package com.catvasiliy.presentation.util

import kotlinx.serialization.Serializable

@Serializable
sealed interface TabPageType {

    @Serializable
    data object RepairOrdersList : TabPageType

    @Serializable
    data class RepairOrderDetails(val repairOrderId: Int) : TabPageType

    @Serializable
    data object ClientsList : TabPageType

    @Serializable
    data class ClientDetails(val clientId: Int) : TabPageType
}