package com.catvasiliy.domain.model.client

data class NewClient(
    val firstName: String,
    val lastName: String,
    val patronymic: String?,
    val address: String,
    val phoneNumber: String
)
