package com.catvasiliy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RepairOrder(
    val id: Int = 0,
    val faultDescription: String,
    val clientId: Int
)
