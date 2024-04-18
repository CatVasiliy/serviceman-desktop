package com.catvasiliy.presentation.repair_order.repair_order_details

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
import com.catvasiliy.domain.model.repair_order.RepairOrder

data class RepairOrderDetailsScreen(private val repairOrderId: Int) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val onNavigateBack: () -> Unit = {
            navigator.pop()
        }

        val screenModel = getScreenModel<RepairOrderDetailsScreenModel>()
        val state by screenModel.state.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.loadRepairOrder(repairOrderId)
        }

        RepairOrderDetailsScreenContent(
            state = state,
            onNavigateBack = onNavigateBack
        )
    }

    @Composable
    private fun RepairOrderDetailsScreenContent(
        state: RepairOrder,
        onNavigateBack: () -> Unit
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            RepairOrderInfoRow(title = "Fault Description", value = state.faultDescription)
            Button(
                onClick = onNavigateBack
            ) {
                Text(
                    text = "Go Back",
                    fontSize = 32.sp
                )
            }
        }
    }

    @Composable
    private fun RepairOrderInfoRow(
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
    private fun RepairOrderDetailsScreenPreview() {
        val repairOrder = RepairOrder(
            faultDescription = "Some device fault description"
        )

        RepairOrderDetailsScreenContent(
            state = repairOrder,
            onNavigateBack = {  }
        )
    }
}