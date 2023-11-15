package com.cornellappdev.scoop.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.cornellappdev.scoop.data.repositories.LoginRepository
import com.cornellappdev.scoop.data.repositories.RideRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val rideRepository: RideRepository,
) : ViewModel() {

    val rideFlow = rideRepository.rideFlow

    suspend fun getRides() {
        rideRepository.getAllRides()
    }

}