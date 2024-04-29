package com.catvasiliy.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.children.ChildNavState
import com.arkivanov.decompose.router.pages.*
import com.arkivanov.decompose.value.Value
import com.catvasiliy.domain.repository.ClientRepository
import com.catvasiliy.domain.repository.RepairOrderRepository
import com.catvasiliy.presentation.client.client_details.ClientDetailsComponent
import com.catvasiliy.presentation.client.clients_list.ClientsListComponent
import com.catvasiliy.presentation.client.create_client.CreateClientComponent
import com.catvasiliy.presentation.repair_order.create_repair_order.CreateRepairOrderComponent
import com.catvasiliy.presentation.repair_order.repair_order_details.RepairOrderDetailsComponent
import com.catvasiliy.presentation.repair_order.repair_orders_list.RepairOrdersListComponent
import com.catvasiliy.presentation.util.TabPage
import com.catvasiliy.presentation.util.TabPageType
import kotlinx.serialization.Serializable

@OptIn(ExperimentalDecomposeApi::class)
class RootComponent(
    componentContext: ComponentContext,
    private val repairOrderRepository: RepairOrderRepository,
    private val clientRepository: ClientRepository
) : ComponentContext by componentContext {

    private val navigation = PagesNavigation<TabPageConfig>()

    val tabPages: Value<ChildPages<*, TabPage>> =
        childPages(
            source = navigation,
            serializer = TabPageConfig.serializer(),
            initialPages = { Pages() },
            pageStatus = ::getTabPageStatus,
            childFactory = ::createChild
        )

    private fun createChild(
        config: TabPageConfig,
        componentContext: ComponentContext
    ) : TabPage {
        return when(config) {
            is TabPageConfig.CreateRepairOrder -> TabPage.CreateRepairOrder(
                component = CreateRepairOrderComponent(
                    componentContext = componentContext,
                    repository = repairOrderRepository
                )
            )
            is TabPageConfig.RepairOrdersList -> TabPage.RepairOrdersList(
                component = RepairOrdersListComponent(
                    componentContext = componentContext,
                    repository = repairOrderRepository,
                    onNavigateToDetails = { newTabPage(TabPageType.RepairOrderDetails(it)) }
                )
            )
            is TabPageConfig.RepairOrderDetails -> TabPage.RepairOrderDetails(
                repairOrderId = config.repairOrderId,
                component = RepairOrderDetailsComponent(config.repairOrderId, componentContext, repairOrderRepository)
            )
            is TabPageConfig.CreateClient -> TabPage.CreateClient(
                component = CreateClientComponent(
                    componentContext = componentContext,
                    repository = clientRepository
                )
            )
            is TabPageConfig.ClientsList -> TabPage.ClientsList(
                component = ClientsListComponent(
                    componentContext = componentContext,
                    repository = clientRepository,
                    onNavigateToDetails = { newTabPage(TabPageType.ClientDetails(it)) }
                )
            )
            is TabPageConfig.ClientDetails -> TabPage.ClientDetails(
                clientId = config.clientId,
                component = ClientDetailsComponent(config.clientId, componentContext, clientRepository)
            )
        }
    }

    fun newTabPage(tabPageType: TabPageType) {
        navigation.navigate(
            transformer = { pages ->
                val existingIndex = pages.items.indexOfFirst { it.tabPageType == tabPageType }
                if (existingIndex != -1) {
                    pages.copy(selectedIndex = existingIndex)
                } else {
                    val newIndex = pages.items.lastIndex + 1
                    val newTabConfig = createNewTabPageConfig(tabPageType)
                    val newPagesItems = pages.items + newTabConfig

                    pages.copy(newPagesItems, newIndex)
                }
            }
        )
    }

    fun selectTabPage(tabPageIndex: Int) {
        navigation.select(tabPageIndex)
    }

    fun closeTabPage(tabPageIndex: Int) {
        navigation.navigate(
            transformer = { pages ->
                val tabToClose = pages.items[tabPageIndex]
                val newPagesItems = pages.items - tabToClose

                pages.copy(newPagesItems, newPagesItems.lastIndex)
            }
        )
    }

    private fun createNewTabPageConfig(tabPageType: TabPageType): TabPageConfig =
        when(tabPageType) {
            is TabPageType.CreateRepairOrder -> TabPageConfig.CreateRepairOrder
            is TabPageType.RepairOrdersList -> TabPageConfig.RepairOrdersList
            is TabPageType.RepairOrderDetails -> TabPageConfig.RepairOrderDetails(tabPageType.repairOrderId)
            is TabPageType.CreateClient -> TabPageConfig.CreateClient
            is TabPageType.ClientsList -> TabPageConfig.ClientsList
            is TabPageType.ClientDetails -> TabPageConfig.ClientDetails(tabPageType.clientId)
        }

    private fun getTabPageStatus(selectedIndex: Int, tabPages: Pages<*>): ChildNavState.Status =
        if (selectedIndex == tabPages.selectedIndex) {
            ChildNavState.Status.RESUMED
        } else {
            ChildNavState.Status.CREATED
        }

    @Serializable
    private sealed class TabPageConfig(val tabPageType: TabPageType) {

        @Serializable
        data object CreateRepairOrder : TabPageConfig(TabPageType.CreateRepairOrder)

        @Serializable
        data object RepairOrdersList : TabPageConfig(TabPageType.RepairOrdersList)

        @Serializable
        data class RepairOrderDetails(val repairOrderId: Int) : TabPageConfig(TabPageType.RepairOrderDetails(repairOrderId))

        @Serializable
        data object CreateClient : TabPageConfig(TabPageType.CreateClient)

        @Serializable
        data object ClientsList : TabPageConfig(TabPageType.ClientsList)

        @Serializable
        data class ClientDetails(val clientId: Int) : TabPageConfig(TabPageType.ClientDetails(clientId))
    }
}