package com.catvasiliy.presentation.client.client_details

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.catvasiliy.domain.model.client.Client

data class ClientDetailsScreen(private val clientId: Int) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val onNavigateBack: () -> Unit = {
            navigator.pop()
        }

        val screenModel = getScreenModel<ClientDetailsScreenModel>()
        val state by screenModel.state.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.loadClient(clientId)
        }

        ClientDetailsScreenContent(
            state = state,
            onNavigateBack = onNavigateBack
        )
    }

    @Composable
    private fun ClientDetailsScreenContent(
        state: Client,
        onNavigateBack: () -> Unit
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

            Button(
                onClick = onNavigateBack
            ) {
                Text(
                    text = " Go Back",
                    fontSize = 32.sp
                )
            }
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
    private fun ClientDetailsScreenPreview() {
        val client = Client(
            firstName = "Penultan",
            lastName = "Minsiron",
            patronymic = "Henober",
            address = "Orapionas, 25",
            phoneNumber = "5893467695"
        )

        ClientDetailsScreenContent(
            state = client,
            onNavigateBack = {  }
        )
    }
}