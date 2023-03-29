package com.cornellappdev.scoop.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Search(
    @Json(name = "start_location_place_id") var departureLocationPlaceId: String? = null,
    @Json(name = "start_location_name") var departureLocationName: String? = null,
    @Json(name = "end_location_place_id") var arrivalLocationPlaceId: String? = null,
    @Json(name = "end_location_name") var arrivalLocationName: String? = null,
    @Json(name = "departure_datetime") var departureDate: String? = null
)