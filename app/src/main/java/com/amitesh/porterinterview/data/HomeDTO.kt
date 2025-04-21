package com.amitesh.porterinterview.data

data class HomeDTO(
    val id: String,
    val placeName: String,
    val userName: String,
    val phoneNumber: String,
    val addressLine: String,
    val pincode: String,
    val tags: List<String>,
    val isMostUsed: Boolean,
    val placeType: String
)
