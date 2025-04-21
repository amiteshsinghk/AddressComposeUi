package com.amitesh.porterinterview.data

import com.amitesh.porterinterview.domain.Home

fun HomeDTO.toHome(): Home {
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

fun Home.toHomeDTO(): HomeDTO {
    return HomeDTO(
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
