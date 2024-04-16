package com.catvasiliy.presentation.client.clients_list

import com.catvasiliy.domain.model.Client
import com.catvasiliy.domain.repository.ClientRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ClientsListViewModel(private val repository: ClientRepository) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _state = MutableStateFlow<List<Client>>(emptyList())
    val state: StateFlow<List<Client>> = _state

    init {
        getClientsList()
    }

    fun getClientsList() {
        coroutineScope.launch {
            _state.update {
                repository.getClientsList()
            }
        }
    }
}