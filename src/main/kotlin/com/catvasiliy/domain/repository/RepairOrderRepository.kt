package com.catvasiliy.domain.repository

import com.catvasiliy.domain.model.repair_order.NewRepairOrder
import com.catvasiliy.domain.model.repair_order.RepairOrder

interface RepairOrderRepository {
    suspend fun getRepairOrdersList(): List<RepairOrder>
    suspend fun getRepairOrderById(id: Int): RepairOrder
    suspend fun createRepairOrder(repairOrder: NewRepairOrder)
}