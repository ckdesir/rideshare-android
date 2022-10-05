package com.cornellappdev.scoop.data.models

import com.squareup.moshi.Json

data class Ride(
    var id: Int? = null,
    var creator: User? = null,
    @Json(name = "max_travelers") var maxTravelers: Int? = null,
    @Json(name = "min_travelers") var minTravelers: Int? = null,
    @Json(name = "departure_datetime") var departureDatetime: String? = null,
    var description: String? = null,
    @Json(name = "is_flexible") var isFlexible: Boolean? = null,
    var type: String? = null,
    var departureLocation: String? = null,
    var arrivalLocation: String? = null,
    var dateOfTrip: String? = null,
    var timeOfTrip: String? = null,
    var path: Path? = null
)
