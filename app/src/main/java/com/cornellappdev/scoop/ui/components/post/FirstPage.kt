package com.cornellappdev.scoop.ui.components.post

import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.RideType
import com.cornellappdev.scoop.data.models.rideTypeToString
import com.cornellappdev.scoop.ui.components.general.CityPicker
import com.cornellappdev.scoop.ui.theme.Green
import com.cornellappdev.scoop.ui.viewmodel.PostScreenViewModel

/**
 * This composable creates the first page of the posting a ride flow which contains the type of
 * ride (either shared uber or driving) as well as the departure and arrival locations.
 */
@Composable
fun FirstPage(
    onProceedClicked: () -> Unit,
    postScreenViewModel: PostScreenViewModel
) {
    val typeText = rememberSaveable {
        mutableStateOf(
            rideTypeToString(postScreenViewModel.ride.type)
        )
    }
    val departureText = rememberSaveable {
        mutableStateOf(
            postScreenViewModel.ride.departureLocationName.orEmpty()
        )
    }
    val arrivalText = rememberSaveable {
        mutableStateOf(
            postScreenViewModel.ride.arrivalLocationName.orEmpty()
        )
    }
    val proceedEnabled = rememberSaveable {
        mutableStateOf(
            postScreenViewModel.ride.departureLocationName != ""
                    && postScreenViewModel.ride.arrivalLocationName != ""
                    && postScreenViewModel.ride.type != null
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(horizontal = 37.dp)
                .padding(top = 240.dp)
        ) {
            TransportationSection(
                postScreenViewModel,
                proceedEnabled,
                typeText,
                modifier = Modifier.onFocusChanged {
                    proceedEnabled.value = postScreenViewModel.ride.departureLocationName != ""
                            && postScreenViewModel.ride.arrivalLocationName != ""
                            && postScreenViewModel.ride.type != null
                    Log.d("type changes", (postScreenViewModel.ride.type != null).toString())
                }
            )
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            CityPicker(
                cityState = departureText,
                placeholder = "Departure location",
                icon = Icons.Filled.NearMe,
                modifier = Modifier.onFocusChanged {
                    postScreenViewModel.setDepartureName(departureText.value)
                },
                disableDivider = true,
                placeholderColor = Color(0xFF001E2D),
                onCityChanged = { name, placeId ->
                    postScreenViewModel.setDepartureName(name)
                    postScreenViewModel.setDeparturePlaceId(placeId)
                    proceedEnabled.value = postScreenViewModel.ride.departureLocationName != ""
                            && postScreenViewModel.ride.arrivalLocationName != ""
                            && postScreenViewModel.ride.type != null
//                    Log.d(
//                        "departure not null",
//                        (postScreenViewModel.ride.departureLocationName != null).toString()
//                    )
//                    Log.d(
//                        "proceed next first page enabled",
//                        proceedEnabled.toString()
//                    )
                }
            )
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            CityPicker(
                cityState = arrivalText,
                placeholder = "Arrival location",
                icon = Icons.Filled.Place,
                modifier = Modifier.onFocusChanged {
                    postScreenViewModel.setArrivalName(arrivalText.value)
                },
                disableDivider = true,
                placeholderColor = Color(0xFF001E2D),
                onCityChanged = { name, placeId ->
                    postScreenViewModel.setArrivalName(name)
                    postScreenViewModel.setArrivalPlaceId(placeId)
                    proceedEnabled.value = postScreenViewModel.ride.departureLocationName != null
                            && postScreenViewModel.ride.arrivalLocationName != null
                            && postScreenViewModel.ride.type != null
//                    Log.d(
//                        "arrival not null",
//                        (postScreenViewModel.ride.arrivalLocationName != null).toString()
//                    )
//                    Log.d(
//                        "proceed next first page enabled",
//                        proceedEnabled.toString()
//                    )
                }
            )

            Column(
                modifier = Modifier
                    .align(Alignment.End)
                    .wrapContentSize()
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 118.dp)
                        .width(86.dp)
                        .height(51.dp),
                    shape = RoundedCornerShape(26.dp),
                    enabled = proceedEnabled.value,
                    onClick = {
                        // Updates trip state with details collected on FirstPage
                        Log.d("FirstPage", "Departure value: $departureText.value")
                        val ride = postScreenViewModel.ride
                        ride.type = when (typeText.value) {
                            "rideshare" -> RideType.RIDESHARE
                            "studentdriver" -> RideType.STUDENT
                            else -> null
                        }
                        // don't know if this works ideally we can set things before next button
                        // postScreenViewModel.setRide(ride)
                        onProceedClicked()
                    },
                    contentPadding = PaddingValues(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFCEE9DC),
                        disabledBackgroundColor = Color(0xFFDBE5DF)
                    )
                ) {
                    Text(
                        "Next",
                        style = MaterialTheme.typography.body1,
                        color = (if (proceedEnabled.value) Color.Black else Color(0xFF001E2D)),
                        fontWeight = Bold
                    )
                }
            }
        }
    }
}

@Composable
fun TransportationSection(
    postScreenViewModel: PostScreenViewModel,
    proceedEnabled: MutableState<Boolean>,
    typeText: MutableState<String>,
    modifier: Modifier = Modifier
) {
    val items = listOf("Student driver", "Taxi")
    // var selectedValue by remember { mutableStateOf(postScreenViewModel.ride.type) }
//    val isSelectedItem: (String) -> Boolean = {
//        typeText.value == when (it) {
//            "Student driver" -> "studentdriver"
//            "Taxi" -> "rideshare"
//            else -> "asdf"
//        }
//    }

    Column {
        Text(
            stringResource(R.string.transportation_method),
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        items.forEach { item ->
            Row(
                verticalAlignment = CenterVertically,
                modifier = modifier
                    .selectable(
                        selected = rideTypeToString(postScreenViewModel.ride.type) == when (item) {
                            "Student driver" -> "studentdriver"
                            "Taxi" -> "rideshare"
                            else -> "asdf"
                        },
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {
                            Log.d(
                                "button initial value",
                                postScreenViewModel.ride.type.toString()
                            )
                            Log.d(
                                "button initial value",
                                rideTypeToString(postScreenViewModel.ride.type)
                            )
                            val type = when (item) {
                                "Student driver" -> "studentdriver"
                                "Taxi" -> "rideshare"
                                else -> ""
                            }
                            postScreenViewModel.setType(type)
                            // selectedValue = stringToRideType(type)
                            proceedEnabled.value =
                                postScreenViewModel.ride.departureLocationName != ""
                                        && postScreenViewModel.ride.arrivalLocationName != ""
                                        && postScreenViewModel.ride.type != null
                            Log.d(
                                "button clicked",
                                (postScreenViewModel.ride.type != null).toString()
                            )
                            Log.d(
                                "button ride type",
                                rideTypeToString(postScreenViewModel.ride.type)
                            )
                            Log.d(
                                "button item",
                                item
                            )
                        },
                        role = Role.RadioButton
                    )
                    .padding(10.dp)
            ) {
                RadioButton(
                    selected = rideTypeToString(postScreenViewModel.ride.type) == when (item) {
                        "Student driver" -> "studentdriver"
                        "Taxi" -> "rideshare"
                        else -> "asdf"
                    },
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Green
                    )
                )
                Spacer(
                    modifier = Modifier.width(14.dp)
                )
                Text(
                    text = item,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}