package com.cornellappdev.scoop.models

data class Trip(
    var methodOfTransportation: String? = null,
    var departureLocation: String? = null,
    var arrivalLocation: String? = null,
    var lowerRangeNumTravelers: Int? = null,
    var higherRangeNumTravelers: Int? = null,
    var dateOfTrip: String? = null,
    var timeOfTrip: String? = null,
    var otherDetails: String? = null
)