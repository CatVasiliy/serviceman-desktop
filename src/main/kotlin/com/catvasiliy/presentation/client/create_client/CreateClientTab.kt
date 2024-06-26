package com.catvasiliy.presentation.client.create_client

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreateClientTab(
    component: CreateClientComponent
) {

    CreateClientScreenContent(
        onCreateClient = component::createClient
    )
}

@Composable
fun CreateClientScreenContent(
    onCreateClient: (String, String, String?, String, String) -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var patronymic by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val fieldTextStyle = TextStyle(fontSize = 24.sp)
        TextField(
            value = firstName,
            onValueChange = { newValue ->
                firstName = newValue
            },
            textStyle = fieldTextStyle
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = lastName,
            onValueChange = { newValue ->
                lastName = newValue
            },
            textStyle = fieldTextStyle
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = patronymic,
            onValueChange = { newValue ->
                patronymic = newValue
            },
            textStyle = fieldTextStyle
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = address,
            onValueChange = { newValue ->
                address = newValue
            },
            textStyle = fieldTextStyle
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = phoneNumber,
            onValueChange = { newValue ->
                phoneNumber = newValue
            },
            textStyle = fieldTextStyle
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = {
                onCreateClient(
                    firstName,
                    lastName,
                    patronymic.ifBlank { null },
                    address,
                    phoneNumber
                )
            }
        ) {
            Text(
                text = "Create Client",
                fontSize = 32.sp
            )
        }
    }
}

@Preview
@Composable
fun CreateClientScreenPreview() {
    CreateClientScreenContent(
        onCreateClient = { _, _, _, _, _ -> }
    )
}