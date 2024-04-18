package com.catvasiliy.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.catvasiliy.presentation.client.ClientTab
import com.catvasiliy.presentation.repair_order.RepairOrderTab

class MainScreen : Screen {

    private val tabAdapter = TabAdapter()

    @Composable
    override fun Content() {
        TabNavigator(TabAdapter.DummyTab()) {
            val tabs by tabAdapter.tabs.collectAsState()
            val currentTab by tabAdapter.currentTab.collectAsState()

            LocalTabNavigator.current.current = currentTab

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                ScrollableTabRow(
                    tabs = tabs
                )
                Button(
                    onClick = { tabAdapter.newTab(RepairOrderTab(tabAdapter.indexOfNext)) }
                ) {
                    Text(text = "New Repair Orders Tab")
                }
                Button(
                    onClick = { tabAdapter.newTab(ClientTab(tabAdapter.indexOfNext)) }
                ) {
                    Text(text = "New Clients Tab")
                }
                if (tabs.isNotEmpty()) {
                    CurrentTab()
                }
            }
        }
    }

    @Composable
    fun ScrollableTabRow(
        tabs: List<ExtendedTab>
    ) {
        LazyRow {
            items(tabs) { tab ->
                TabItem(
                    tab = tab
                )
            }
        }
    }

    @Composable
    fun TabItem(
        tab: ExtendedTab
    ) {
        val tabNavigator = LocalTabNavigator.current
        val title = tab.title
        val isSelected = tabNavigator.current == tab

        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { tabAdapter.selectTab(tab) }
                .hoverable(interactionSource)
        ) {
            Text(
                text = title,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            val closeAlpha = if (isSelected || isHovered) 1f else 0f
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier
                    .clickable { tabAdapter.closeTab(tab) }
                    .alpha(closeAlpha)
            )
        }
    }
}