package com.amitesh.porterinterview.presentation

data class HomeListState(
    val homeUi: List<HomeUi> = emptyList(),
    val searchQuery: String = ""
)
