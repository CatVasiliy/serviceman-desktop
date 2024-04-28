package com.catvasiliy.presentation.repair_order.repair_orders_list

import com.arkivanov.decompose.ComponentContext
import com.catvasiliy.domain.model.repair_order.RepairOrder
import com.catvasiliy.domain.repository.RepairOrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepairOrdersListComponent(
    componentContext: ComponentContext,
    private val repository: RepairOrderRepository,
    private val onNavigateToDetails: (Int) -> Unit
) : ComponentContext by componentContext {

    private val componentScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _state = MutableStateFlow<List<RepairOrder>>(emptyList())
    val state = _state.asStateFlow()

    init {
        getRepairOrdersList()
    }

    fun getRepairOrdersList() {
        componentScope.launch {
            _state.update {
                repository.getRepairOrdersList()
            }
        }
    }

    fun navigateToDetails(repairOrderId: Int) {
        onNavigateToDetails(repairOrderId)
    }
}