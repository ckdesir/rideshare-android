package com.cornellappdev.scoop.data.models

data class User(
    var id: Int? = null,
    var netid: String? = null,
    var first_name: String? = null,
    var last_name: String? = null,
    var phone_number: String? = null,
    var grade: String? = null,
    var profile_pic_url: String? = null,
    var pronouns: String? = null
)