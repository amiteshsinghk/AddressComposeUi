package com.amitesh.porterinterview.presentation

sealed interface HomeListAction {
    data class OnDeleteAction(val homeUi: HomeUi) : HomeListAction
    data class OnSearchTextChange(val searchQuery: String) : HomeListAction
    data object OnClearAction : HomeListAction
}