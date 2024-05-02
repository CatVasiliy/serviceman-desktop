package com.catvasiliy.presentation.client.create_client

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.catvasiliy.domain.model.client.NewClient
import com.catvasiliy.domain.repository.ClientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CreateClientComponent(
    componentContext: ComponentContext,
    private val repository: ClientRepository
) : ComponentContext by componentContext {

    private val componentScope = coroutineScope(Dispatchers.Main + SupervisorJob())

    fun createClient(
        firstName: String,
        lastName: String,
        patronymic: String?,
        address: String,
        phoneNumber: String
    ) {
        val client = NewClient(
            firstName = firstName,
            lastName = lastName,
            patronymic = patronymic,
            address = address,
            phoneNumber = phoneNumber
        )

        componentScope.launch {
            repository.createClient(client)
        }
    }
}