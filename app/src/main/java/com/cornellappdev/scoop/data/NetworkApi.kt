package com.cornellappdev.scoop.data

import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.data.models.RideRequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkApi {
    @POST("/api/ride/")
    suspend fun createRide(@Body rideRequestBody: RideRequestBody): Ride
}