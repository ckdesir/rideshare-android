package com.cornellappdev.scoop.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.data.models.User
import com.cornellappdev.scoop.ui.components.post.MapRideCard
import com.cornellappdev.scoop.ui.theme.ScoopTheme
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            ProvideWindowInsets {
                ScoopTheme {
                    val placeholderUser = User(
                        id = 0,
                        netid = "mp774",
                        firstName = "Maxwell",
                        lastName = "Pang",
                        phoneNumber = "6464311636",
                        email = "mp774@cornell.edu",
                        grade = "sophomore",
                        profilePicUrl = null,
                        pronouns = "he/him",
                        hometown = "New York, NY",
                        song = "drunk by keshi",
                        snack = "kit kat",
                        stop = "Shake Shack"
                    )
                    val placeholderRide = Ride(
                        id = 0,
                        creator = placeholderUser,
                        maxTravelers = 10,
                        minTravelers = 1,
                        departureDatetime = "12:00:00 11/11/2022",
                        description = "help me pls",
                        isFlexible = true,
                        type = "Rideshare",
                        departureLocation = "Ithaca, NY",
                        arrivalLocation = "New York, NY",
                        dateOfTrip = "11/11/2022",
                        timeOfTrip = "12:00:00",
                        path = null
                    )

                    MapRideCard(rideState = remember { mutableStateOf(placeholderRide) })
                }
            }
        }
    }
}



