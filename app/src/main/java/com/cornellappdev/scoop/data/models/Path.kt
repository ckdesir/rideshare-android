package com.cornellappdev.scoop.data.models

data class Path(
    var start_location_place_id: String? = null,
    var start_location_name: String? = null,
    var end_location_place_id: String? = null,
    var end_location_name: String? = null
)
