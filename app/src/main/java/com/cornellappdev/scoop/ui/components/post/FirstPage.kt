package com.cornellappdev.scoop.ui.components.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.models.Trip
import com.cornellappdev.scoop.ui.components.general.BuildMessage
import com.cornellappdev.scoop.ui.components.general.DenseTextField
import com.cornellappdev.scoop.ui.components.general.SelectField
import com.cornellappdev.scoop.ui.theme.Gray
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FirstPage(onProceedClicked : () -> Unit, tripState: MutableState<Trip>) {
    val methodOfTransportation =
        rememberSaveable { mutableStateOf((tripState.value.departureLocation ?: "")) }
    val departureLocation =
        rememberSaveable { mutableStateOf((tripState.value.departureLocation ?: "")) }
    val arrivalLocation =
        rememberSaveable { mutableStateOf((tripState.value.arrivalLocation ?: "")) }
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
            TextFields(methodOfTransportation)
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
                                // Updates trip state with details collected on SecondPage
                                val trip = tripState.value
                                trip.methodOfTransportation = methodOfTransportation.value
                                trip.departureLocation = departureLocation.value
                                trip.arrivalLocation = arrivalLocation.value
                                tripState.value = trip
                                onProceedClicked()
                            }
                        }
                    },
                    contentPadding = PaddingValues(10.dp),
                    colors = buttonColors(backgroundColor = Gray)
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
fun TextFields(methodOfTransportation : MutableState<String>) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        Text(
            text = "Method of Transportation",
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 22.sp
        )
        SelectField(listOf("Car", "Uber", "Train", "Bicycle"), methodOfTransportation)
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Locations",
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(14.dp))
        Row {
            Icon(
                imageVector = Icons.Outlined.NearMe,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 5.dp)
            )
            DenseTextField(
                "", {},
                "departure location...",
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        Row {
            Icon(
                imageVector = Icons.Outlined.Place,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 5.dp)
            )
            DenseTextField(
                "", {},
                "arrival location...",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}