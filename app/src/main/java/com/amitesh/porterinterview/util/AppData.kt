package com.amitesh.porterinterview.util

import com.amitesh.porterinterview.presentation.HomeUi

object AppData{
    fun getData() = mutableListOf<HomeUi>(
        HomeUi(
            id = "1",
            placeName = "Galaxy Plaza Mall",
            userName = "Ravi Sharma",
            phoneNumber = "1234567890",
            addressLine = "Skyline Fitness Hub, 10th Avenue, Mumbai, Maharashtra",
            pincode = "400001",
            tags = listOf("Hardware", "FMCG", "Gifts"),
            isMostUsed = true,
            placeType = "OTHERS"
        ),
        HomeUi(
            id = "2",
            placeName = "Vega City Mall",
            userName = "Anurag Kapoor",
            phoneNumber = "9876543210",
            addressLine = "Citi Nest Sports Centre, 7th Cross Road, Bangalore, Karnataka",
            pincode = "560095",
            tags = listOf("Hardware", "FMCG", "Gifts"),
            isMostUsed = false,
            placeType = "SHOP"

        ),
        HomeUi(
            id = "3",
            placeName = "Nova Plaza Mall",
            userName = "Rohan Mehta",
            phoneNumber = "1234567890",
            addressLine = "Galaxy Sports Complex, 9th Avenue, Mumbai, Maharashtra",
            pincode = "400045",
            tags = listOf("Hardware", "FMCG", "Gifts"),
            isMostUsed = false,
            placeType = "HOME"
        )

    )
}