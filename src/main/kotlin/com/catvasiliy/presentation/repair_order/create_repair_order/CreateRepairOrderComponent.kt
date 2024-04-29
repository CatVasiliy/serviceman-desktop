package com.catvasiliy.presentation.repair_order.create_repair_order

import com.arkivanov.decompose.ComponentContext
import com.catvasiliy.domain.model.repair_order.NewRepairOrder
import com.catvasiliy.domain.repository.RepairOrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CreateRepairOrderComponent(
    componentContext: ComponentContext,
    private val repository: RepairOrderRepository
) : ComponentContext by componentContext {

    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun createRepairOrder(
        faultDescription: String,
        clientId: Int
    ) {
        val repairOrder = NewRepairOrder(
            faultDescription = faultDescription,
            clientId = clientId
        )

        coroutineScope.launch {
            repository.createRepairOrder(repairOrder)
        }
    }
}