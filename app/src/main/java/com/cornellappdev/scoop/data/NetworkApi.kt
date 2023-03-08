package com.cornellappdev.scoop.data

import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.data.models.RideRequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkApi {
    @POST("/api/ride/")
    suspend fun createRide(@Body rideRequestBody: RideRequestBody): Ride

    @GET("/api/rides/")
    suspend fun getAllRides(): List<Ride>

    @GET("/api/ride/{ride_id}")
    suspend fun getRide(@Path("ride_id") rideId: Int): Ride
}