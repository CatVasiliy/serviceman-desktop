package com.catvasiliy.presentation.client.clients_list

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catvasiliy.domain.model.client.Client

@Composable
fun ClientsListTab(
    component: ClientsListComponent
) {

    val state by component.state.collectAsState()

    ClientsListTabContent(
        state = state,
        onRefresh = component::loadClientsList,
        onNavigateToDetails = component::navigateToDetails
    )
}

@Composable
fun ClientsListTabContent(
    state: List<Client>,
    onRefresh: () -> Unit,
    onNavigateToDetails: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            val lazyColumnState = rememberLazyListState()
            LazyColumn(
                state = lazyColumnState,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxSize()
            ) {
                items(state) { client ->
                    ClientListItem(
                        client = client,
                        onNavigateToDetails = onNavigateToDetails
                    )
                }
            }
            VerticalScrollbar(
                adapter = rememberScrollbarAdapter(lazyColumnState),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight()
            )
        }
        Button(
            onClick = onRefresh
        ) {
            Text(
                text = "Refresh Clients",
                fontSize = 32.sp
            )
        }
    }
}
@Composable
fun ClientListItem(
    client: Client,
    onNavigateToDetails: (Int) -> Unit
) {
    val shortName = "${client.lastName} ${client.firstName.first()}. ${client.patronymic?.first()}."

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onNavigateToDetails(client.id) }
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

@Preview
@Composable
private fun ClientsListTabPreview() {
    val client = Client(
        firstName = "Penultan",
        lastName = "Minsiron",
        patronymic = "Henober",
        address = "Orapionas, 25",
        phoneNumber = "5893467695"
    )

    val clientsList = buildList {
        repeat(20) {
            add(client)
        }
    }

    ClientsListTabContent(
        state = clientsList,
        onRefresh = {  },
        onNavigateToDetails = {  }
    )
}