package com.catvasiliy.di

import com.catvasiliy.data.ClientRepositoryImpl
import com.catvasiliy.data.RepairOrderRepositoryImpl
import com.catvasiliy.domain.repository.ClientRepository
import com.catvasiliy.domain.repository.RepairOrderRepository
import com.catvasiliy.presentation.util.tab_pages.TabPageFactory
import com.catvasiliy.presentation.util.tab_pages.factories.ClientTabPageFactory
import com.catvasiliy.presentation.util.tab_pages.factories.RepairOrderTabPageFactory
import com.catvasiliy.presentation.util.tab_pages.factories.TabPageFactoryImpl
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
    single<ClientRepository> { ClientRepositoryImpl(get()) }

    factory<RepairOrderTabPageFactory> { RepairOrderTabPageFactory(get()) }
    factory<ClientTabPageFactory> { ClientTabPageFactory(get()) }

    factory<TabPageFactory> { TabPageFactoryImpl(get(), get()) }
}