package com.catvasiliy.presentation.client

import androidx.compose.runtime.Composable
import com.catvasiliy.presentation.client.client_details.ClientDetailsTab
import com.catvasiliy.presentation.client.clients_list.ClientsListTab
import com.catvasiliy.presentation.client.create_client.CreateClientTab
import com.catvasiliy.presentation.util.tab_pages.ClientTabPage

@Composable
fun ClientTab(
    tabPage: ClientTabPage
) {
    when(tabPage) {
        is ClientTabPage.CreateClient -> CreateClientTab(tabPage.component)
        is ClientTabPage.ClientsList -> ClientsListTab(tabPage.component)
        is ClientTabPage.ClientDetails -> ClientDetailsTab(tabPage.component)
    }
}