package com.catvasiliy.presentation.repair_order.create_repair_order

import com.catvasiliy.domain.model.RepairOrder
import com.catvasiliy.domain.repository.RepairOrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CreateRepairOrderViewModel(private val repository: RepairOrderRepository) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun createRepairOrder(
        faultDescription: String,
        clientId: Int
    ) {
        val repairOrder = RepairOrder(
            faultDescription = faultDescription,
            clientId = clientId
        )

        coroutineScope.launch {
            repository.createRepairOrder(repairOrder)
        }
    }
}