package com.catvasiliy.presentation.repair_order.repair_order_details

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.catvasiliy.domain.model.repair_order.RepairOrder
import com.catvasiliy.domain.repository.RepairOrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepairOrderDetailsScreenModel(private val repository: RepairOrderRepository) : ScreenModel {

    private val _state = MutableStateFlow(RepairOrder())
    val state = _state.asStateFlow()

    fun loadRepairOrder(id: Int) {
        screenModelScope.launch {
            _state.update {
                repository.getRepairOrderById(id)
            }
        }
    }
}