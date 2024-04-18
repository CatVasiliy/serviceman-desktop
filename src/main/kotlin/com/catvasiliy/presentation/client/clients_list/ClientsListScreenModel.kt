package com.catvasiliy.presentation.client.clients_list

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.catvasiliy.domain.model.client.Client
import com.catvasiliy.domain.repository.ClientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ClientsListScreenModel(private val repository: ClientRepository) : ScreenModel {

    private val _state = MutableStateFlow<List<Client>>(emptyList())
    val state = _state.asStateFlow()

    init {
        loadClientsList()
    }

    fun loadClientsList() {
        screenModelScope.launch {
            _state.update {
                repository.getClientsList()
            }
        }
    }
}