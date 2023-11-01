package com.cornellappdev.scoop.data.repositories

import com.cornellappdev.scoop.data.NetworkApi
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.data.models.RideRequestBody
import com.cornellappdev.scoop.data.models.RideType
import com.cornellappdev.scoop.data.models.User
import com.cornellappdev.scoop.ui.viewmodel.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

    private val _rideFlow: MutableStateFlow<ApiResponse<List<Ride>>> =
        MutableStateFlow(ApiResponse.Pending)

    val rideFlow = _rideFlow.asStateFlow()


    init {
        fetchAllRides()
    }

    /**
     * Makes a new call to backend for all rides.
     */
    private fun fetchAllRides() {
        _rideFlow.value = ApiResponse.Pending
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val rides = getAllRides()
                _rideFlow.value = ApiResponse.Success(rides)
            } catch (_: Exception) {
                _rideFlow.value = ApiResponse.Error
            }
        }
    }
}