package com.catvasiliy.presentation.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun TabPages(
    tabPagesValue: Value<ChildPages<*, TabPage>>,
    onSelect: (TabPageType) -> Unit,
    onClose: (TabPageType) -> Unit,
    modifier: Modifier = Modifier,
    tabPageContent: @Composable (tab: TabPage) -> Unit
) {
    val state = tabPagesValue.subscribeAsState()

    val childTabPages by state
    val selectedIndex = childTabPages.selectedIndex

    val tabPages = remember(childTabPages.items) {
        childTabPages.items.map {
            it.instance!!
        }
    }
    val selectedTabPageType = tabPages.getOrNull(selectedIndex)?.tabPageType

    Column(
        modifier = modifier
    ) {
        if (tabPages.isNotEmpty()) {
            ScrollableTabPagesRow(
                tabPages = tabPages,
                selectedTabPageType = selectedTabPageType!!,
                onSelect = onSelect,
                onClose = onClose
            )
            tabPageContent(tabPages[selectedIndex])
        }
    }
}

@Composable
fun ScrollableTabPagesRow(
    tabPages: List<TabPage>,
    selectedTabPageType: TabPageType,
    onSelect: (TabPageType) -> Unit,
    onClose: (TabPageType) -> Unit
) {
    LazyRow {
        items(tabPages) { tab ->
            TabPageItem(
                tabPage = tab,
                selectedTabPageType = selectedTabPageType,
                onSelect = onSelect,
                onClose = onClose
            )
        }
    }
}

@Composable
fun TabPageItem(
    tabPage: TabPage,
    selectedTabPageType: TabPageType,
    onSelect: (TabPageType) -> Unit,
    onClose: (TabPageType) -> Unit
) {

    val isSelected = tabPage.tabPageType == selectedTabPageType

    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onSelect(tabPage.tabPageType) }
            .hoverable(interactionSource)
    ) {
        Text(
            text = tabPage.tabPageTitle,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        val closeAlpha = if (isSelected || isHovered) 1f else 0f
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            modifier = Modifier
                .clickable { onClose(tabPage.tabPageType) }
                .alpha(closeAlpha)
        )
    }
}