package com.catvasiliy.presentation.client.clients_list

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catvasiliy.domain.model.Client
import org.koin.compose.koinInject

@Composable
fun ClientsListScreen() {
    val viewModel = koinInject<ClientsListViewModel>()
    val clientsListState = viewModel.state.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val lazyColumnState = rememberLazyListState()
        LazyColumn(
            state = lazyColumnState,
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(clientsListState.value) { client ->
                ClientListItem(client)
            }
        }
        VerticalScrollbar(
            adapter = rememberScrollbarAdapter(lazyColumnState),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight()
        )
        ExtendedFloatingActionButton(
            text = { Text(text = "Refresh Clients") },
            onClick = { viewModel.getClientsList() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun ClientListItem(client: Client) {
    val shortName = "${client.lastName} ${client.firstName.first()}. ${client.patronymic?.first()}."

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = shortName,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = client.address,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = client.phoneNumber,
            fontSize = 32.sp
        )
    }
}