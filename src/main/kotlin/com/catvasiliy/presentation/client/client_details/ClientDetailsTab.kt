package com.catvasiliy.presentation.client.client_details

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
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
fun ClientDetailsTab(
    component: ClientDetailsComponent
) {
    val state by component.state.collectAsState()

    ClientDetailsTabContent(state = state)
}

@Composable
private fun ClientDetailsTabContent(
    state: Client
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        ClientInfoRow(title = "ID:", value = state.id.toString())
        ClientInfoRow(title = "First Name:", value = state.firstName)
        ClientInfoRow(title = "Last Name:", value = state.lastName)
        if (state.patronymic != null) {
            ClientInfoRow(title = "Patronymic:", value = state.patronymic)
        }
        ClientInfoRow(title = "Address:", value = state.address)
        ClientInfoRow(title = "Phone Number:", value = state.phoneNumber)
    }
}

@Composable
private fun ClientInfoRow(
    title: String,
    value: String
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            fontSize = 40.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = value,
            fontSize = 32.sp
        )
    }
}

@Preview
@Composable
private fun ClientDetailsTabPreview() {
    val client = Client(
        firstName = "Penultan",
        lastName = "Minsiron",
        patronymic = "Henober",
        address = "Orapionas, 25",
        phoneNumber = "5893467695"
    )

    ClientDetailsTabContent(
        state = client
    )
}