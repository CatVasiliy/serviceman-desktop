package com.catvasiliy.presentation.util.tab_pages

import com.arkivanov.decompose.ComponentContext

interface TabPageFactory {
    fun createTabPage(
        tabPageConfig: TabPageConfig,
        componentContext: ComponentContext,
        onNavigate: (TabPageConfig) -> Unit
    ): TabPage
}