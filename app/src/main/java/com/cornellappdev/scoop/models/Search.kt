package com.cornellappdev.scoop.models

data class Search(
    var departureLocation: String? = null,
    var arrivalLocation: String? = null,
    var departureDate: String? = null
)