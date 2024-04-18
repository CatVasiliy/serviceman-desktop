package com.catvasiliy.presentation.client

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.catvasiliy.presentation.ExtendedTab
import com.catvasiliy.presentation.client.clients_list.ClientsListScreen

class ClientTab(index: Int) : ExtendedTab(index, "Client") {

    @Composable
    override fun Content() {
        Navigator(ClientsListScreen())
    }
}