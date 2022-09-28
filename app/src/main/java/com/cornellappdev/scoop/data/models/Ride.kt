package com.cornellappdev.scoop.data.models

data class Ride(
    var id: Int? = null,
    var creator: User? = null,
    var max_travelers: Int? = null,
    var min_travelers: Int? = null,
    var departure_datetime: String? = null,
    var description: String? = null,
    var is_flexible: Boolean? = null,
    var type: String? = null,
    var departureLocation: String? = null,
    var arrivalLocation: String? = null,
    var dateOfTrip: String? = null,
    var timeOfTrip: String? = null,
    var path: Path? = null
)
