package com.catvasiliy.presentation.util.tab_pages.factories

import com.arkivanov.decompose.ComponentContext
import com.catvasiliy.domain.repository.ClientRepository
import com.catvasiliy.presentation.client.client_details.ClientDetailsComponent
import com.catvasiliy.presentation.client.clients_list.ClientsListComponent
import com.catvasiliy.presentation.client.create_client.CreateClientComponent
import com.catvasiliy.presentation.util.tab_pages.ClientConfig
import com.catvasiliy.presentation.util.tab_pages.ClientTabPage
import com.catvasiliy.presentation.util.tab_pages.TabPageConfig

class ClientTabPageFactory(
    private val repository: ClientRepository
) {
    fun createTabPage(
        tabPageConfig: ClientConfig,
        componentContext: ComponentContext,
        onNavigate: (TabPageConfig) -> Unit
    ): ClientTabPage {
        return when (tabPageConfig) {
            is ClientConfig.CreateClient -> ClientTabPage.CreateClient(
                component = CreateClientComponent(
                    componentContext = componentContext,
                    repository = repository
                )
            )
            is ClientConfig.ClientsList -> ClientTabPage.ClientsList(
                component = ClientsListComponent(
                    componentContext = componentContext,
                    repository = repository,
                    onNavigateToDetails = { onNavigate(ClientConfig.ClientDetails(clientId = it)) }
                )
            )
            is ClientConfig.ClientDetails -> ClientTabPage.ClientDetails(
                component = ClientDetailsComponent(
                    componentContext = componentContext,
                    repository = repository,
                    clientId = tabPageConfig.clientId
                ),
                clientId = tabPageConfig.clientId
            )
        }
    }
}