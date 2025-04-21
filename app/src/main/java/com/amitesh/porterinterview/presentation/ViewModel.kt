package com.amitesh.porterinterview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amitesh.porterinterview.data.HomeRepositoryImpl
import com.amitesh.porterinterview.domain.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository = HomeRepositoryImpl()
) : ViewModel() {

    private val _state = MutableStateFlow(HomeListState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val homes = repository.getAllData()
            _state.value = _state.value.copy(
                homeUi = homes.map { it.toHomeUi() }
            )
        }
    }

    fun onActionClick(action: HomeListAction) {
        when (action) {
            is HomeListAction.OnSearchTextChange -> searchQuery(action.searchQuery)
            HomeListAction.OnClearAction -> clearText()
            is HomeListAction.OnDeleteAction -> deleteItem(action.homeUi)
        }
    }

    private fun searchQuery(query: String) {
        viewModelScope.launch{
            _state.update { it.copy(searchQuery = query) }
            val result = repository.searchQuery(query)
            _state.update { it.copy(homeUi = result.map { h -> h.toHomeUi() }) }
        }
    }

    fun clearText() {
        viewModelScope.launch{
            val all = repository.clearSearch()
            _state.value = _state.value.copy(
                searchQuery = "",
                homeUi = all.map { it.toHomeUi() }
            )
        }
    }

    private fun deleteItem(homeUi: HomeUi) {
        viewModelScope.launch {
            repository.deleteHome(homeUi.toHome())
            val result = repository.searchQuery(_state.value.searchQuery)
            _state.value = _state.value.copy(homeUi = result.map { it.toHomeUi() })
        }
    }

}