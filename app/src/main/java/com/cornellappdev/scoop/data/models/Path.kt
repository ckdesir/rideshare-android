package com.cornellappdev.scoop.data.models

import com.squareup.moshi.Json

data class Path(
    @Json(name = "start_location_place_id") var startLocationPlaceId: String? = null,
    @Json(name = "start_location_name") var startLocationName: String? = null,
    @Json(name = "end_location_place_id") var endLocationPlaceId: String? = null,
    @Json(name = "end_location_name") var endLocationName: String? = null
)
