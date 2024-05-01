package com.catvasiliy.presentation.util.tab_pages.factories

import com.arkivanov.decompose.ComponentContext
import com.catvasiliy.presentation.util.tab_pages.*

class TabPageFactoryImpl(
    private val repairOrderTabPageFactory: RepairOrderTabPageFactory,
    private val clientTabPageFactory: ClientTabPageFactory
) : TabPageFactory {
    override fun createTabPage(
        tabPageConfig: TabPageConfig,
        componentContext: ComponentContext,
        onNavigate: (TabPageConfig) -> Unit
    ): TabPage {
        return when(tabPageConfig) {
            is RepairOrderConfig -> repairOrderTabPageFactory.createTabPage(
                tabPageConfig = tabPageConfig,
                componentContext = componentContext,
                onNavigate = onNavigate
            )
            is ClientConfig -> clientTabPageFactory.createTabPage(
                tabPageConfig = tabPageConfig,
                componentContext = componentContext,
                onNavigate = onNavigate
            )
        }
    }
}