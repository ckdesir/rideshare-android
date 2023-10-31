package com.cornellappdev.scoop.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class RideRequestBody(
    @Json(name = "start_location_place_id") var departureLocationPlaceId: String? = null,
    @Json(name = "start_location_name") var departureLocationName: String? = null,
    @Json(name = "end_location_place_id") var arrivalLocationPlaceId: String? = null,
    @Json(name = "end_location_name") var arrivalLocationName: String? = null,
    @Json(name = "creator") var creator: Int? = null,
    @Json(name = "max_travelers") var maxTravelers: Int? = null,
    @Json(name = "min_travelers") var minTravelers: Int? = null,
    @Json(name = "type") var type: RideType? = null,
    @Json(name = "is_flexible") var isFlexible: Boolean? = null,
    @Json(name = "departure_datetime") var datetime: String? = null,
    @Json(name = "description") var description: String? = null
)

enum class RideType {
    @Json(name = "rideshare")
    RIDESHARE,

    @Json(name = "studentdriver")
    STUDENT
}

fun rideTypeToString(type: RideType?): String {
    return when (type) {
        RideType.RIDESHARE -> "rideshare"
        RideType.STUDENT -> "studentdriver"
        else -> "null"
    }
}

fun stringToRideType(string: String): RideType? {
    return when (string) {
        "rideshare" -> RideType.RIDESHARE
        "studentdriver" -> RideType.STUDENT
        else -> null
    }
}
