package com.catvasiliy.data

import com.catvasiliy.domain.model.RepairOrder
import com.catvasiliy.domain.repository.RepairOrderRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val ENDPOINT = "repair-orders"

class RepairOrderRepositoryImpl(private val httpClient: HttpClient) : RepairOrderRepository {

    override suspend fun getRepairOrdersList(): List<RepairOrder> = withContext(Dispatchers.IO) {
        httpClient.get(ENDPOINT).body<List<RepairOrder>>()
    }

    override suspend fun getRepairOrderById(id: Int): RepairOrder = withContext(Dispatchers.IO) {
        httpClient.get("$ENDPOINT/$id").body<RepairOrder>()
    }

    override suspend fun createRepairOrder(repairOrder: RepairOrder): Unit = withContext(Dispatchers.IO) {
        httpClient.post(ENDPOINT) {
            contentType(ContentType.Application.Json)
            setBody(repairOrder)
        }
    }
}