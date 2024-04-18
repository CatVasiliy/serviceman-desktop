package com.catvasiliy.presentation.client.client_details

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.catvasiliy.domain.model.client.Client
import com.catvasiliy.domain.repository.ClientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ClientDetailsScreenModel(private val repository: ClientRepository) : ScreenModel {

    private val _state = MutableStateFlow(Client())
    val state = _state.asStateFlow()

    fun loadClient(id: Int) {
        screenModelScope.launch {
            _state.update {
                repository.getClientById(id)
            }
        }
    }
}