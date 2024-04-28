package com.catvasiliy.presentation.repair_order.repair_order_details

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
import com.catvasiliy.domain.model.repair_order.RepairOrder

@Composable
fun RepairOrderDetailsTab(
    component: RepairOrderDetailsComponent
) {
    val state by component.state.collectAsState()

    RepairOrderDetailsTabContent(
        state = state
    )
}

@Composable
private fun RepairOrderDetailsTabContent(
    state: RepairOrder
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        RepairOrderInfoRow(title = "Fault Description", value = state.faultDescription)
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
private fun RepairOrderDetailsTabPreview() {
    val repairOrder = RepairOrder(
        faultDescription = "Some device fault description"
    )

    RepairOrderDetailsTabContent(
        state = repairOrder
    )
}