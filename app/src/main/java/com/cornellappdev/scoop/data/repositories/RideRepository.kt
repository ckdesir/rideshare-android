package com.cornellappdev.scoop.data.repositories

import com.cornellappdev.scoop.data.NetworkApi
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.data.models.RideRequestBody
import com.cornellappdev.scoop.data.models.RideType
import com.cornellappdev.scoop.data.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RideRepository @Inject constructor(private val networkApi: NetworkApi) {

    suspend fun createRide(
        type: RideType,
        startLocationPlaceId: String,
        startLocationName: String,
        endLocationPlaceId: String,
        endLocationName: String,
        maxTravelers: Int,
        minTravelers: Int,
        departureDateTime: String,
        isFlexible: Boolean,
        creator: Int,
    ): Ride =
        networkApi.createRide(
            RideRequestBody(
                startLocationPlaceId,
                startLocationName,
                endLocationPlaceId,
                endLocationName,
                creator,
                maxTravelers,
                minTravelers,
                type,
                isFlexible,
                departureDateTime
            )
        )

    suspend fun getAllRides(): List<Ride> = networkApi.getAllRides()
}