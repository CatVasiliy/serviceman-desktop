package com.catvasiliy.domain.model.client

import kotlinx.serialization.Serializable

@Serializable
data class NewClient(
    val firstName: String,
    val lastName: String,
    val patronymic: String?,
    val address: String,
    val phoneNumber: String
)
