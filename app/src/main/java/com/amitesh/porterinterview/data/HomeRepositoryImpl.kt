package com.amitesh.porterinterview.data

import com.amitesh.porterinterview.domain.Home
import com.amitesh.porterinterview.domain.HomeRepository
import com.amitesh.porterinterview.presentation.toHomeList
import com.amitesh.porterinterview.util.AppData

class HomeRepositoryImpl : HomeRepository {
    private val originalList = AppData.getData()

    override suspend fun getAllData(): List<Home> {
        return originalList.toHomeList()
    }

    override suspend fun deleteHome(home: Home) {
        originalList.removeIf { it.id == home.id }
    }

    override suspend fun searchQuery(query: String): List<Home> {
        return if (query.length >= 4) {
            originalList.filter { it.placeName.contains(query, ignoreCase = true) }.toHomeList()
        } else originalList.toHomeList()
    }

    override suspend fun clearSearch(): List<Home> {
        return originalList.toHomeList()
    }
}