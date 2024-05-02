package com.catvasiliy.presentation.client.clients_list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.catvasiliy.domain.model.client.Client
import com.catvasiliy.domain.repository.ClientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ClientsListComponent(
    private val componentContext: ComponentContext,
    private val repository: ClientRepository,
    private val onNavigateToDetails: (Int) -> Unit
) : ComponentContext by componentContext {

    private val componentScope = coroutineScope(Dispatchers.Main + SupervisorJob())

    private val _state = MutableStateFlow<List<Client>>(emptyList())
    val state = _state.asStateFlow()

    init {
        loadClientsList()
    }

    fun loadClientsList() {
        componentScope.launch {
            _state.update {
                repository.getClientsList()
            }
        }
    }

    fun navigateToDetails(clientId: Int) {
        onNavigateToDetails(clientId)
    }
}