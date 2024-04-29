package com.catvasiliy.presentation.client.create_client

import com.arkivanov.decompose.ComponentContext
import com.catvasiliy.domain.model.client.NewClient
import com.catvasiliy.domain.repository.ClientRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CreateClientComponent(
    componentContext: ComponentContext,
    private val repository: ClientRepository
) : ComponentContext by componentContext {

    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

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

        coroutineScope.launch {
            repository.createClient(client)
        }
    }
}