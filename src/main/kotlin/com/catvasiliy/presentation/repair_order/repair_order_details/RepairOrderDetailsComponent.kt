package com.catvasiliy.presentation.repair_order.repair_order_details

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

class RepairOrderDetailsComponent(
    private val componentContext: ComponentContext,
    private val repository: RepairOrderRepository,
    private val repairOrderId: Int
) : ComponentContext by componentContext {

    private val componentScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _state = MutableStateFlow(RepairOrder())
    val state = _state.asStateFlow()

    init {
        getRepairOrder()
    }

    private fun getRepairOrder() {
        componentScope.launch {
            _state.update {
                repository.getRepairOrderById(repairOrderId)
            }
        }
    }
}