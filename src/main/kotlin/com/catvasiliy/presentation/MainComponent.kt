package com.catvasiliy.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.children.ChildNavState
import com.arkivanov.decompose.router.pages.*
import com.arkivanov.decompose.value.Value
import com.catvasiliy.presentation.util.tab_pages.TabPage
import com.catvasiliy.presentation.util.tab_pages.TabPageConfig
import com.catvasiliy.presentation.util.tab_pages.TabPageFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@OptIn(ExperimentalDecomposeApi::class)
class MainComponent(
    private val componentContext: ComponentContext
) : ComponentContext by componentContext, KoinComponent {

    private val tabPageFactory: TabPageFactory by inject()

    private val navigation = PagesNavigation<TabPageConfig>()

    val tabPages: Value<ChildPages<TabPageConfig, TabPage>> =
        childPages(
            source = navigation,
            serializer = TabPageConfig.serializer(),
            initialPages = { Pages() },
            pageStatus = ::getTabPageStatus,
            childFactory = ::createTabPage
        )

    fun newTabPage(newTabPageConfig: TabPageConfig) {
        navigation.navigate(
            transformer = { tabPages ->
                addNewTabPage(tabPages = tabPages, newTabPageConfig = newTabPageConfig)
            }
        )
    }

    fun selectTabPage(tabPageIndex: Int) {
        navigation.select(tabPageIndex)
    }

    fun closeTabPage(tabPageIndex: Int) {
        navigation.navigate(
            transformer = { tabPages ->
                removeTabPage(tabPages = tabPages, tabPageIndexToRemove = tabPageIndex)
            }
        )
    }

    private fun createTabPage(
        tabPageConfig: TabPageConfig,
        componentContext: ComponentContext
    ) : TabPage {
        return tabPageFactory.createTabPage(
            tabPageConfig = tabPageConfig,
            componentContext = componentContext,
            onNavigate = { newTabPage(newTabPageConfig = it) }
        )
    }

    private fun getTabPageStatus(selectedIndex: Int, tabPages: Pages<*>): ChildNavState.Status =
        if (selectedIndex == tabPages.selectedIndex) {
            ChildNavState.Status.RESUMED
        } else {
            ChildNavState.Status.CREATED
        }

    private fun addNewTabPage(tabPages: Pages<TabPageConfig>, newTabPageConfig: TabPageConfig): Pages<TabPageConfig> {
        val tabPagesConfigList = tabPages.items

        val existingIndex = tabPagesConfigList.indexOfFirst { it == newTabPageConfig }

        return if (existingIndex != -1) {
            tabPages.copy(selectedIndex = existingIndex)
        } else {
            val newSelectedIndex = tabPagesConfigList.lastIndex + 1
            val newTabPagesConfigList = tabPagesConfigList + newTabPageConfig

            tabPages.copy(items = newTabPagesConfigList, selectedIndex = newSelectedIndex)
        }
    }

    private fun removeTabPage(tabPages: Pages<TabPageConfig>, tabPageIndexToRemove: Int): Pages<TabPageConfig> {
        val tabPagesConfigList = tabPages.items

        val newSelectedIndex = if (tabPageIndexToRemove == 0) {
            if (tabPagesConfigList.size == 1) -1 else 0
        } else {
            tabPageIndexToRemove - 1
        }

        val tabPageToRemove = tabPagesConfigList[tabPageIndexToRemove]
        val newTabPagesConfigList = tabPagesConfigList - tabPageToRemove

        return tabPages.copy(items = newTabPagesConfigList, selectedIndex = newSelectedIndex)
    }
}