package com.catvasiliy.presentation.repair_order.create_repair_order

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
fun CreateRepairOrderTab(
    component: CreateRepairOrderComponent
) {
    CreateRepairOrderScreenContent(
        onCreateRepairOrder = component::createRepairOrder
    )
}

@Composable
fun CreateRepairOrderScreenContent(
    onCreateRepairOrder: (String, Int) -> Unit
) {
    var faultDescription by remember { mutableStateOf("") }
    var clientId by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val textFieldStyle = TextStyle(fontSize = 24.sp)

        TextField(
            value = faultDescription,
            onValueChange = { newValue ->
                faultDescription = newValue
            },
            textStyle = textFieldStyle
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = clientId,
            onValueChange = { newValue ->
                clientId = newValue
            },
            textStyle = textFieldStyle
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = {
                onCreateRepairOrder(
                    faultDescription,
                    clientId.toInt()
                )
            }
        ) {
            Text(
                text = "Create Repair Order",
                fontSize = 32.sp
            )
        }
    }
}

@Preview
@Composable
fun CreateRepairOrderScreenPreview() {
    CreateRepairOrderScreenContent(
        onCreateRepairOrder = { _, _ -> }
    )
}