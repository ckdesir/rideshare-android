package com.cornellappdev.scoop.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cornellappdev.scoop.data.models.RideRequestBody
import com.cornellappdev.scoop.data.models.stringToRideType
import com.cornellappdev.scoop.data.repositories.RideRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This class creates the ViewModel for the posting a ride flow.
 */
@HiltViewModel
class PostScreenViewModel @Inject constructor(private val rideRepository: RideRepository) :
    ViewModel() {

    private val _ride: RideRequestBody by mutableStateOf(RideRequestBody())
    val ride: RideRequestBody = _ride

    fun createARide() = viewModelScope.launch {
        if (assertReadyToPost()) {
            rideRepository.createRide(
                ride.type!!,
                ride.departureLocationName!!,
                ride.departureLocationPlaceId!!,
                ride.arrivalLocationName!!,
                ride.arrivalLocationPlaceId!!,
                ride.maxTravelers!!,
                ride.minTravelers!!,
                ride.datetime!!,
                ride.isFlexible!!,
                ride.creator!!,
            )
        }
    }

    private fun assertReadyToPost(): Boolean {
        return ride.type != null
                && ride.departureLocationName != null
                && ride.departureLocationPlaceId != null
                && ride.arrivalLocationName != null
                && ride.arrivalLocationPlaceId != null
                && ride.maxTravelers != null
                && ride.minTravelers != null
                && ride.datetime != null
                && ride.description != null
                && ride.isFlexible != null
                && ride.creator != null
    }

    fun setType(type: String) {
        _ride.type = stringToRideType(type)
    }

    fun setDepartureName(departure: String) {
        _ride.departureLocationName = departure
    }

    fun setDeparturePlaceId(departurePlaceId: String) {
        _ride.departureLocationPlaceId = departurePlaceId
    }

    fun setArrivalName(arrival: String) {
        _ride.arrivalLocationName = arrival
    }

    fun setArrivalPlaceId(arrivalPlaceId: String) {
        _ride.arrivalLocationPlaceId = arrivalPlaceId
    }

    fun setMinTravelers(minTravelers: Int) {
        _ride.minTravelers = minTravelers
    }

    fun setMaxTravelers(maxTravelers: Int) {
        _ride.maxTravelers = maxTravelers
    }

    fun setDescription(description: String) {
        _ride.description = description
    }

    fun setDatetime(datetime: String) {
        _ride.datetime = datetime
    }
}