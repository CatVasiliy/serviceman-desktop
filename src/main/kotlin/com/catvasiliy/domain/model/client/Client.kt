package com.catvasiliy.domain.model.client

import kotlinx.serialization.Serializable

@Serializable
data class Client(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val patronymic: String? = null,
    val address: String = "",
    val phoneNumber: String = ""
)
