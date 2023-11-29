package com.cornellappdev.scoop.data.models

import com.squareup.moshi.Json

data class User(
    @Json(name = "id") var id: Int? = null,
    @Json(name = "netid") var netid: String? = null,
    @Json(name = "first_name") var firstName: String? = null,
    @Json(name = "last_name") var lastName: String? = null,
    @Json(name = "phone_number") var phoneNumber: String? = null,
    @Json(name = "grade") var grade: String? = null,
    @Json(name = "profile_pic_url") var profilePicUrl: String? = null,
    var email: String? = null,
    var pronouns: String? = null,
    var hometown: String? = null,
    var song: String? = null,
    var snack: String? = null,
    var stop: String? = null,
    var talkativity: Float = 0.5F,
    var musicAffinity: Float = 0.5F
)