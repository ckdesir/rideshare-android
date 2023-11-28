package com.cornellappdev.scoop.data

import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.data.models.RideRequestBody
import com.cornellappdev.scoop.data.models.Search
import com.cornellappdev.scoop.data.repositories.AuthenticateResponse
import com.cornellappdev.scoop.data.repositories.SignedInUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkApi {
    @POST("/api/ride/")
    suspend fun createRide(@Body rideRequestBody: RideRequestBody): Ride

    @GET("/api/rides/")
    suspend fun getAllRides(@HeaderMap headers: Map<String, String>): List<Ride>

    @GET("/api/ride/{ride_id}")
    suspend fun getRide(@Path("ride_id") rideId: Int): Ride

    @GET("/api/search/")
    suspend fun searchForRides(@Body searchBody: Search): List<Ride>
}