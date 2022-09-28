package com.cornellappdev.scoop.data.models

data class User(
    var id: Int? = null,
    var netid: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var phoneNumber: String? = null,
    var grade: String? = null,
    var profilePicUrl: String? = null,
    var pronouns: String? = null
)