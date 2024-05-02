package com.catvasiliy.presentation.repair_order.create_repair_order

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.catvasiliy.domain.model.repair_order.NewRepairOrder
import com.catvasiliy.domain.repository.RepairOrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CreateRepairOrderComponent(
    componentContext: ComponentContext,
    private val repository: RepairOrderRepository
) : ComponentContext by componentContext {

    private val componentScope = coroutineScope(Dispatchers.Main + SupervisorJob())

    fun createRepairOrder(
        faultDescription: String,
        clientId: Int
    ) {
        val repairOrder = NewRepairOrder(
            faultDescription = faultDescription,
            clientId = clientId
        )

        componentScope.launch {
            repository.createRepairOrder(repairOrder)
        }
    }
}