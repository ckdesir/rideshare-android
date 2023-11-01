package com.cornellappdev.scoop.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.ui.components.general.RideCard
import com.cornellappdev.scoop.ui.components.home.TripPostedConfirmation
import com.cornellappdev.scoop.ui.theme.Gray
import com.cornellappdev.scoop.ui.viewmodel.ApiResponse
import com.cornellappdev.scoop.ui.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onTripClick: (String) -> Unit,
    onPostNewRide: () -> Unit,
    showTripPosted: Boolean = false,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    var tripPostedMessageShown by rememberSaveable { mutableStateOf(showTripPosted) }

    // Collect flow of rides through API
    val rideApiResponse = homeScreenViewModel.rideFlow.collectAsState().value

    val testData = listOf(Ride(departureLocationName = "Ithaca", arrivalLocationName = "Syracuse", datetime = "Nov 13"), Ride(departureLocationName = "Ithaca", arrivalLocationName = "Syracuse", datetime = "Nov 13"), Ride(departureLocationName = "Ithaca", arrivalLocationName = "Syracuse", datetime = "Nov 13"), Ride(departureLocationName = "Ithaca", arrivalLocationName = "Syracuse", datetime = "Nov 13"), Ride(departureLocationName = "Ithaca", arrivalLocationName = "Syracuse", datetime = "Nov 13"), Ride(departureLocationName = "Ithaca", arrivalLocationName = "Syracuse", datetime = "Nov 13"))

    // Stops displaying the given trip posted message to the user after a delay.
    suspend fun stopDisplayingTripPosted() {
        if (tripPostedMessageShown) {
            delay(3000L)
            tripPostedMessageShown = false
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(18.dp)
                    .size(70.dp),
                onClick = onPostNewRide,
                backgroundColor = Gray,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_new_trip),
                    contentDescription = "Post a new trip"
                )
            }
        }
    ) {

        val state = rememberLazyListState()

        LazyColumn(
            state = state,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(16.dp)
        ) {

            when (rideApiResponse) {
                is ApiResponse.Error -> items(emptyList<Ride>()) {
                    item -> RideCard(item)
                }
                is ApiResponse.Pending -> items(emptyList<Ride>()) {
                        item -> RideCard(item)
                }
                is ApiResponse.Success -> items(rideApiResponse.data) { item ->
                    RideCard(item)
                }
            }
        }
        TripPostedConfirmation(showTripPosted = tripPostedMessageShown)
        LaunchedEffect(key1 = Unit, block = {
            stopDisplayingTripPosted()
        })
    }
}