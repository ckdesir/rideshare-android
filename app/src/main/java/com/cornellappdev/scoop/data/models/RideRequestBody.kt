package com.cornellappdev.scoop.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RideRequestBody(
    @Json(name = "start_location_place_id") val startLocationPlaceId: String,
    @Json(name = "start_location_name") val startLocationName: String,
    @Json(name = "end_location_place_id") val endLocationPlaceId: String,
    @Json(name = "end_location_name") val endLocationName: String,
    @Json(name = "creator") val creator: Int,
    @Json(name = "max_travelers") val maxTravelers: Int,
    @Json(name = "min_travelers") val minTravelers: Int,
    @Json(name = "type") val type: RideType,
    @Json(name = "is_flexible") val isFlexible: Boolean,
    @Json(name = "departure_datetime") val departureDateTime: String
)

enum class RideType {
    @Json(name = "rideshare")
    RIDESHARE,

    @Json(name = "studentdriver")
    STUDENT
}
