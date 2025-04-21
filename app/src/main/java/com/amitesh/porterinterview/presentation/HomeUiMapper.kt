package com.amitesh.porterinterview.presentation

import com.amitesh.porterinterview.domain.Home

fun Home.toHomeUi(): HomeUi {
    return HomeUi(
        id = id,
        placeName = placeName,
        userName = userName,
        phoneNumber = phoneNumber,
        addressLine = addressLine,
        pincode = pincode,
        tags = tags,
        isMostUsed = isMostUsed,
        placeType = placeType
    )
}

fun HomeUi.toHome(): Home {
    return Home(
        id = id,
        placeName = placeName,
        userName = userName,
        phoneNumber = phoneNumber,
        addressLine = addressLine,
        pincode = pincode,
        tags = tags,
        isMostUsed = isMostUsed,
        placeType = placeType
    )
}

fun List<Home>.toHomeUiList(): List<HomeUi> = map { it.toHomeUi() }

fun List<HomeUi>.toHomeList(): List<Home> = map { it.toHome() }
