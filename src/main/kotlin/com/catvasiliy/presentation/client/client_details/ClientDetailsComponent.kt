package com.catvasiliy.presentation.client.client_details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.catvasiliy.domain.model.client.Client
import com.catvasiliy.domain.repository.ClientRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ClientDetailsComponent(
    private val componentContext: ComponentContext,
    private val repository: ClientRepository,
    private val clientId: Int
) : ComponentContext by componentContext {

    private val componentScope = coroutineScope(Dispatchers.Main + SupervisorJob())

    private val _state = MutableStateFlow(Client())
    val state = _state.asStateFlow()

    init {
        getClient()
    }

    private fun getClient() {
        componentScope.launch {
            _state.update {
                repository.getClientById(clientId)
            }
        }
    }
}