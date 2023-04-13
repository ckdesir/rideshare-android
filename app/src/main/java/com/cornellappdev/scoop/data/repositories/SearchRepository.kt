package com.cornellappdev.scoop.data.repositories

import com.cornellappdev.scoop.data.NetworkApi
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.data.models.Search
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(private val networkApi: NetworkApi) {

    suspend fun searchForRides(
        startLocationPlaceId: String,
        startLocationName: String,
        endLocationPlaceId: String,
        endLocationName: String,
        departureDateTime: String,
    ): List<Ride> =
        networkApi.searchForRides(
            Search(
                startLocationPlaceId,
                startLocationName,
                endLocationPlaceId,
                endLocationName,
                departureDateTime
            )
        )

    suspend fun getAllRides(): List<Ride> = networkApi.getAllRides()
}