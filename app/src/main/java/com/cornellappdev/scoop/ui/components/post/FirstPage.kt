package com.cornellappdev.scoop.ui.components.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.models.Trip
import com.cornellappdev.scoop.ui.components.general.BuildMessage
import com.cornellappdev.scoop.ui.components.general.CityPicker
import com.cornellappdev.scoop.ui.components.general.Spinner
import com.cornellappdev.scoop.ui.theme.Green
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FirstPage(onProceedClicked: () -> Unit, tripState: MutableState<Trip>) {
    val methodOfTransportation =
        rememberSaveable { mutableStateOf(tripState.value.departureLocation.orEmpty()) }
    val departureLocation =
        rememberSaveable { mutableStateOf(tripState.value.departureLocation.orEmpty()) }
    val arrivalLocation =
        rememberSaveable { mutableStateOf(tripState.value.arrivalLocation.orEmpty()) }
    var showInvalidDepartureMessage by rememberSaveable { mutableStateOf(false) }
    var showInvalidArrivalMessage by rememberSaveable { mutableStateOf(false) }
    var showInvalidMethodMessage by rememberSaveable { mutableStateOf(false) }
    var proceedEnabled by rememberSaveable { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    // Stops displaying the given message to the user after a delay.
    suspend fun disableMessage() {
        delay(3000L)
        when {
            showInvalidMethodMessage -> {
                showInvalidMethodMessage = false
                proceedEnabled = true
            }
            showInvalidDepartureMessage -> {
                showInvalidDepartureMessage = false
                proceedEnabled = true
            }
            showInvalidArrivalMessage -> {
                showInvalidArrivalMessage = false
                proceedEnabled = true
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .wrapContentSize()
                .padding(horizontal = 37.dp)
        ) {
            TextFields(
                methodOfTransportation,
                departureLocation,
                arrivalLocation,
                tripState
            )
            Column(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 38.dp)
                    .wrapContentSize()
            ) {
                Button(
                    modifier = Modifier
                        .size(56.dp),
                    shape = RoundedCornerShape(30.dp),
                    enabled = proceedEnabled,
                    onClick = {
                        when {
                            methodOfTransportation.value.isEmpty() -> {
                                showInvalidMethodMessage = true
                                proceedEnabled = false
                                coroutineScope.launch {
                                    disableMessage()
                                }
                            }
                            departureLocation.value.isEmpty() -> {
                                showInvalidDepartureMessage = true
                                proceedEnabled = false
                                coroutineScope.launch {
                                    disableMessage()
                                }
                            }
                            arrivalLocation.value.isEmpty() -> {
                                showInvalidArrivalMessage = true
                                proceedEnabled = false
                                coroutineScope.launch {
                                    disableMessage()
                                }
                            }
                            else -> {
                                onProceedClicked()
                            }
                        }
                    },
                    contentPadding = PaddingValues(10.dp),
                    colors = buttonColors(backgroundColor = Green, contentColor = Color.White)
                ) {
                    Icon(
                        Icons.Outlined.ArrowForward,
                        contentDescription = stringResource(R.string.arrow_forward_description)
                    )
                }
            }
        }

        Box {
            BuildMessage(
                showInvalidMethodMessage,
                stringResource(R.string.invalid_method_of_transportation)
            )
            BuildMessage(showInvalidDepartureMessage, stringResource(R.string.invalid_departure))
            BuildMessage(showInvalidArrivalMessage, stringResource(R.string.invalid_arrival))
        }
    }
}

@Composable
fun TextFields(
    methodOfTransportation: MutableState<String>,
    departureLocation: MutableState<String>,
    arrivalLocation: MutableState<String>,
    tripState: MutableState<Trip>
) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        Text(
            text = stringResource(R.string.method_of_transportation),
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 22.sp
        )
        Spinner(methodOfTransportation, listOf("Car", "Uber", "Train", "Bicycle")) {
            tripState.value = tripState.value.copy(
                methodOfTransportation = it
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(R.string.locations),
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(14.dp))
        Row {
            Icon(
                imageVector = Icons.Outlined.NearMe,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(32.dp)
                    .align(Alignment.CenterVertically)
            )
            CityPicker(
                departureLocation,
                stringResource(R.string.departure_location),
            ) {
                tripState.value = tripState.value.copy(departureLocation = it)
            }
        }
        Row {
            Icon(
                imageVector = Icons.Outlined.Place,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(32.dp)
                    .align(Alignment.CenterVertically)
            )
            CityPicker(
                arrivalLocation,
                stringResource(R.string.arrival_location),
            ) {
                tripState.value = tripState.value.copy(arrivalLocation = it)
            }
        }
    }
}
