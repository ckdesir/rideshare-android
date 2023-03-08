package com.cornellappdev.scoop.data.models

import com.squareup.moshi.Json

// TODO: fix this so that it actually makes sense and correlates with backend haha

data class Ride(
    var id: Int? = null,
    var type: RideType? = null,
    @Json(name = "start_location_place_id") var departureLocationPlaceId: String? = null,
    @Json(name = "start_location_name") var departureLocationName: String? = null,
    @Json(name = "end_location_place_id") var arrivalLocationPlaceId: String? = null,
    @Json(name = "end_location_name") var arrivalLocationName: String? = null,
    @Json(name = "max_travelers") var maxTravelers: Int? = null,
    @Json(name = "min_travelers") var minTravelers: Int? = null,
    @Json(name = "departure_datetime") var datetime: String? = null,
    var description: String? = null,
    @Json(name = "is_flexible") var isFlexible: Boolean? = null,
    var creator: User? = null,
    var path: Path? = null
)
