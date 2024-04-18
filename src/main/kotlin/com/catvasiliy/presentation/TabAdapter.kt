package com.catvasiliy.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TabAdapter {

    private val _tabs: MutableStateFlow<List<ExtendedTab>> = MutableStateFlow(emptyList())
    val tabs = _tabs.asStateFlow()

    private val _currentTab: MutableStateFlow<ExtendedTab> = MutableStateFlow(DummyTab())
    val currentTab = _currentTab.asStateFlow()

    val indexOfNext: Int
        get() = tabs.value.lastIndex

    fun newTab(tab: ExtendedTab) {
        _tabs.update { tabsList -> tabsList + tab }
        _currentTab.update { tab }
    }

    fun selectTab(tab: ExtendedTab) {
        _currentTab.update { tab }
    }

    fun closeTab(tab: ExtendedTab) {
        _tabs.update { tabsList ->
            if (tabsList.size > 1) {
                val tabIndex = tabsList.indexOfFirst { tabEntry -> tabEntry.index == tab.index }
                _currentTab.update {
                    if (tabIndex != 0) tabsList[tabIndex - 1] else tabsList[1]
                }
            }
            tabsList - tab
        }
    }

    class DummyTab : ExtendedTab(-1, "DUMMY") {

        @Composable
        override fun Content() {

        }
    }
}

abstract class ExtendedTab(
    val index: Int,
    val title: String
) : Tab {

    override val options: TabOptions
        @Composable get() = remember {
            TabOptions(
                index = 0u,
                title = title
            )
        }
}