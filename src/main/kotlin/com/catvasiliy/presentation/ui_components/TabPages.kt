package com.catvasiliy.presentation.ui_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.catvasiliy.presentation.util.tab_pages.TabPage

@OptIn(ExperimentalDecomposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun TabPages(
    tabPagesValue: Value<ChildPages<*, TabPage>>,
    onSelect: (Int) -> Unit,
    onClose: (Int) -> Unit,
    modifier: Modifier = Modifier,
    tabPageContent: @Composable (tab: TabPage) -> Unit
) {
    val state = tabPagesValue.subscribeAsState()

    val childTabPages by state
    val selectedIndex = childTabPages.selectedIndex
    val itemsSize = childTabPages.items.size

    if (selectedIndex != -1) {

        val pagerState = rememberPagerState(
            initialPage = selectedIndex,
            pageCount = { itemsSize }
        )

        LaunchedEffect(selectedIndex) {
            if (pagerState.currentPage != selectedIndex) {
                pagerState.scrollToPage(selectedIndex)
            }
        }

        Column(
            modifier = modifier
        ) {
            ScrollableTabPagesRow(
                childTabPages = childTabPages,
                onSelect = onSelect,
                onClose = onClose
            )

            HorizontalPager(
                state = pagerState,
                beyondBoundsPageCount = 32,
                userScrollEnabled = false
            ) { pageIndex ->

                val instance = childTabPages.items[pageIndex].instance

                instance?.let { tabPage ->
                    tabPageContent(tabPage)
                }
            }
        }
    }
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun ScrollableTabPagesRow(
    childTabPages: ChildPages<*, TabPage>,
    onSelect: (Int) -> Unit,
    onClose: (Int) -> Unit
) {
    LazyRow {
        itemsIndexed(childTabPages.items) { index, child ->
            child.instance?.let { tabPage ->
                TabPageItem(
                    tabPage = tabPage,
                    isSelected = index == childTabPages.selectedIndex,
                    onSelect = { onSelect(index) },
                    onClose = { onClose(index) }
                )
            }
        }
    }
}

@Composable
fun TabPageItem(
    tabPage: TabPage,
    isSelected: Boolean,
    onSelect: () -> Unit,
    onClose: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onSelect() }
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
                .clickable { onClose() }
                .alpha(closeAlpha)
        )
    }
}