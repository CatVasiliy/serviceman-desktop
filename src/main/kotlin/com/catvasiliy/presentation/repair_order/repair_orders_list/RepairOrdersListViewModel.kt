package com.catvasiliy.presentation.repair_order.repair_orders_list

import com.catvasiliy.domain.model.RepairOrder
import com.catvasiliy.domain.repository.RepairOrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepairOrdersListViewModel(private val repository: RepairOrderRepository) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _state = MutableStateFlow<List<RepairOrder>>(emptyList())
    val state: StateFlow<List<RepairOrder>> = _state

    init {
        getRepairOrdersList()
    }

    fun getRepairOrdersList() {
        coroutineScope.launch {
            _state.update {
                repository.getRepairOrdersList()
            }
        }
    }
}