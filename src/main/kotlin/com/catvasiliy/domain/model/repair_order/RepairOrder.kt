package com.catvasiliy.domain.model.repair_order

import com.catvasiliy.domain.model.client.Client
import kotlinx.serialization.Serializable

@Serializable
data class RepairOrder(
    val id: Int = 0,
    val faultDescription: String = "",
    val client: Client = Client()
)