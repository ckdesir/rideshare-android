package com.cornellappdev.scoop.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cornellappdev.scoop.data.models.Search
import com.cornellappdev.scoop.data.repositories.SearchRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchScreenViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private val _search: Search by mutableStateOf(Search())
    val search: Search = _search

    fun createARide() = viewModelScope.launch {
        if (assertReadyToPost()) {
            searchRepository.searchForRides(
                search.departureLocationPlaceId!!,
                search.departureLocationName!!,
                search.arrivalLocationPlaceId!!,
                search.arrivalLocationName!!,
                search.departureDate!!,
            )
        }
    }

    private fun assertReadyToPost(): Boolean {
        return search.departureLocationName != null
                && search.departureLocationPlaceId != null
                && search.arrivalLocationName != null
                && search.arrivalLocationPlaceId != null
                && search.departureDate != null
    }

    fun setDepartureName(departure: String) {
        _search.departureLocationName = departure
    }

    fun setDeparturePlaceId(departurePlaceId: String) {
        _search.departureLocationPlaceId = departurePlaceId
    }

    fun setArrivalName(arrival: String) {
        _search.arrivalLocationName = arrival
    }

    fun setArrivalPlaceId(arrivalPlaceId: String) {
        _search.arrivalLocationPlaceId = arrivalPlaceId
    }

    fun setDepartureDate(date: String) {
        _search.departureDate = date
    }
}