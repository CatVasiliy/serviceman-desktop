package com.catvasiliy.di

import com.catvasiliy.data.ClientRepositoryImpl
import com.catvasiliy.data.RepairOrderRepositoryImpl
import com.catvasiliy.domain.repository.ClientRepository
import com.catvasiliy.domain.repository.RepairOrderRepository
import com.catvasiliy.presentation.client.client_details.ClientDetailsScreenModel
import com.catvasiliy.presentation.client.clients_list.ClientsListScreenModel
import com.catvasiliy.presentation.client.create_client.CreateClientViewModel
import com.catvasiliy.presentation.repair_order.create_repair_order.CreateRepairOrderViewModel
import com.catvasiliy.presentation.repair_order.repair_order_details.RepairOrderDetailsScreenModel
import com.catvasiliy.presentation.repair_order.repair_orders_list.RepairOrdersListScreenModel
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.dsl.module

val appModule = module {

    single<HttpClient> {
        HttpClient(CIO) {
            defaultRequest {
                url("http://localhost:8080/")
            }
            install(ContentNegotiation) {
                json()
            }
            install(Logging)
        }
    }

    single<RepairOrderRepository> { RepairOrderRepositoryImpl(get()) }
    single { RepairOrdersListScreenModel(get()) }
    factory { RepairOrderDetailsScreenModel(get()) }
    single { CreateRepairOrderViewModel(get()) }

    single<ClientRepository> { ClientRepositoryImpl(get()) }
    factory { ClientsListScreenModel(get()) }
    factory { ClientDetailsScreenModel(get()) }
    single { CreateClientViewModel(get()) }
}