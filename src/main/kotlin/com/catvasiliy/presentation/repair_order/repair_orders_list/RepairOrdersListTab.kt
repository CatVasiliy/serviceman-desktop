package com.catvasiliy.presentation.repair_order.repair_orders_list

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catvasiliy.domain.model.repair_order.RepairOrder

@Composable
fun RepairOrdersListTab(
    component: RepairOrdersListComponent
) {
    val state by component.state.collectAsState()

    RepairOrdersTabContent(
        state = state,
        onRefresh = component::getRepairOrdersList,
        onNavigateToDetails = component::navigateToDetails
    )
}

@Composable
fun RepairOrdersTabContent(
    state: List<RepairOrder>,
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
                items(state) { repairOrder ->
                    RepairOrderListItem(
                        repairOrder = repairOrder,
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
            onClick = onRefresh,
        ) {
            Text(
                text = "Refresh Repair Orders",
                fontSize = 32.sp
            )
        }
    }
}

@Composable
fun RepairOrderListItem(
    repairOrder: RepairOrder,
    onNavigateToDetails: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onNavigateToDetails(repairOrder.id) }
    ) {
        Text(
            text = repairOrder.id.toString(),
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = repairOrder.faultDescription,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 32.sp
        )
    }
}

@Preview
@Composable
fun RepairOrdersListTabPreview() {
    val repairOrder = RepairOrder(
        faultDescription = "Some device fault description"
    )
    val repairOrdersList = buildList {
        repeat(30) {
            add(repairOrder)
        }
    }

    RepairOrdersTabContent(
        state = repairOrdersList,
        onRefresh = {  },
        onNavigateToDetails = {  }
    )
}