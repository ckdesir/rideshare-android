package com.cornellappdev.scoop.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.cornellappdev.scoop.data.models.stringToDate
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDateTime

class TimeAdapter {

    @ToJson
    fun toJson(time: LocalDateTime): String {
        //TODO: Make sure this matches with how backend stores dates
        return time.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @FromJson
    fun fromJson(time: String): LocalDateTime {
        return stringToDate(time)
    }
}