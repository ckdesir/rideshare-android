package com.cornellappdev.scoop.data.models

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.Json
import java.time.LocalDate
import java.time.LocalDateTime

// TODO: Convert all dates from Strings to LocalDateTime

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

/*
 * Backward compatibility functions for Dates
 */

/**
 * Convert a String to Kotlin LocalDateTime
 */
@RequiresApi(Build.VERSION_CODES.O)
fun stringToDate(dateString : String) : LocalDateTime {
    var splitString = dateString.split(" ")
    var dateList = splitString[0].split("/")
    var timeList = splitString[1].split(":")
    var pm = splitString[2] == "PM"
    var hour = if(pm) Integer.parseInt(timeList[0]) + 12 else Integer.parseInt(timeList[0])
    // Year, Month, Day, Hour, Minute
    return LocalDateTime.of(Integer.parseInt(dateList[2]), Integer.parseInt(dateList[0]), Integer.parseInt(dateList[1]), hour, Integer.parseInt(timeList[1]))
}




