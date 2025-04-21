package com.amitesh.porterinterview.domain

interface HomeRepository {
    suspend fun getAllData(): List<Home>
    suspend fun deleteHome(home: Home)
    suspend fun searchQuery(query: String): List<Home>
    suspend fun clearSearch(): List<Home>
}