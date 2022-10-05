package com.cornellappdev.scoop.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cornellappdev.scoop.data.models.Ride
import kotlinx.coroutines.launch
import java.io.IOException

class PostScreenViewModel() : ViewModel() {

    data class PostScreenUiState(
        val ride: Ride = Ride(),
        val message: String = ""
    )

    var postUiState = mutableStateOf(PostScreenUiState())
        private set

    fun postTrip() = viewModelScope.launch {
        try {
            postUiState.value = postUiState.value.copy(

            )
        } catch (e: IOException) {
            // Can have a more elaborate error state than just a string. This is
            // just a simple example. Get creative 😃!
            postUiState.value = postUiState.value.copy(
                message = e.stackTraceToString()
            )
        }
    }


}