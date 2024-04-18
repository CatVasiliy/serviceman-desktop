package com.catvasiliy.presentation.repair_order.repair_orders_list

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.catvasiliy.domain.model.repair_order.RepairOrder
import com.catvasiliy.domain.repository.RepairOrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepairOrdersListScreenModel(private val repository: RepairOrderRepository) : ScreenModel {

    private val _state = MutableStateFlow<List<RepairOrder>>(emptyList())
    val state = _state.asStateFlow()

    init {
        getRepairOrdersList()
    }

    fun getRepairOrdersList() {
        screenModelScope.launch {
            _state.update {
                repository.getRepairOrdersList()
            }
        }
    }
}