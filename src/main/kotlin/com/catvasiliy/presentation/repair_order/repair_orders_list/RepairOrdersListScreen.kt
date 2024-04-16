package com.catvasiliy.presentation.repair_order.repair_orders_list

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catvasiliy.domain.model.RepairOrder
import org.koin.compose.koinInject

@Composable
fun RepairOrdersListScreen() {
    val viewModel = koinInject<RepairOrdersListViewModel>()
    val repairOrdersListState = viewModel.state.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val lazyColumnState = rememberLazyListState()

        LazyColumn(
            state = lazyColumnState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(repairOrdersListState.value) { repairOrder ->
                RepairOrderListItem(repairOrder)
            }
        }
        VerticalScrollbar(
            adapter = rememberScrollbarAdapter(lazyColumnState),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight()
        )
    }
}

@Composable
fun RepairOrderListItem(repairOrder: RepairOrder) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = repairOrder.id.toString(),
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = repairOrder.faultDescription,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 32.sp
        )
    }
}