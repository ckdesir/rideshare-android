package com.cornellappdev.scoop.ui.components.post

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.models.Trip
import com.cornellappdev.scoop.ui.components.general.BuildMessage
import com.cornellappdev.scoop.ui.components.general.DenseTextField
import com.cornellappdev.scoop.ui.theme.Gray
import com.cornellappdev.scoop.ui.theme.PlaceholderGray
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SecondPage(onProceedClicked: () -> Unit, tripState: MutableState<Trip>) {
    val dateFormatter =
        SimpleDateFormat(stringResource(R.string.month_day_year_no_leading_zero_format), Locale.US)
    val timeFormatter =
        SimpleDateFormat(stringResource(R.string.hour_minute_period_format), Locale.US)
    val dateAndTimeFormatter =
        SimpleDateFormat(stringResource(R.string.date_time_format), Locale.US)
    val (detailsText, setDetailsText) = rememberSaveable { mutableStateOf(tripState.value.otherDetails.orEmpty()) }
    val lowerRangeNumTravelers =
        rememberSaveable { mutableStateOf((tripState.value.lowerRangeNumTravelers ?: 1)) }
    val higherRangeNumTravelers =
        rememberSaveable { mutableStateOf((tripState.value.higherRangeNumTravelers ?: 1)) }
    val (dateText, setDateText) = rememberSaveable { mutableStateOf(tripState.value.dateOfTrip.orEmpty()) }
    val (timeText, setTimeText) = rememberSaveable { mutableStateOf(tripState.value.timeOfTrip.orEmpty()) }
    var showInvalidRangeMessage by rememberSaveable { mutableStateOf(false) }
    var showInvalidDateMessage by rememberSaveable { mutableStateOf(false) }
    var showInvalidTimeMessage by rememberSaveable { mutableStateOf(false) }
    var proceedEnabled by rememberSaveable { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    // Stops displaying the given message to the user after a delay.
    suspend fun disableMessage() {
        delay(3000L)
        when {
            showInvalidRangeMessage -> {
                showInvalidRangeMessage = false
                proceedEnabled = true
            }
            showInvalidDateMessage -> {
                showInvalidDateMessage = false
                proceedEnabled = true
            }
            showInvalidTimeMessage -> {
                showInvalidTimeMessage = false
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
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(horizontal = 37.dp)
        ) {

            NumberOfTravelersSection(lowerRangeNumTravelers, higherRangeNumTravelers)
            DateOfTripSection(dateText, setDateText, dateFormatter)
            TimeOfTripSection(timeText, setTimeText, timeFormatter)
            OtherDetailsSection(detailsText, setDetailsText)

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
                            lowerRangeNumTravelers.value > higherRangeNumTravelers.value -> {
                                showInvalidRangeMessage = true
                                proceedEnabled = false
                                coroutineScope.launch {
                                    disableMessage()
                                }
                            }
                            dateText.isEmpty() -> {
                                showInvalidDateMessage = true
                                proceedEnabled = false
                                coroutineScope.launch {
                                    disableMessage()
                                }
                            }
                            timeText.isEmpty() || dateAndTimeFormatter.parse("$dateText $timeText")
                                ?.before(Date()) == true
                            -> {
                                showInvalidTimeMessage = true
                                proceedEnabled = false
                                coroutineScope.launch {
                                    disableMessage()
                                }
                            }
                            else -> {
                                // Updates trip state with details collected on SecondPage
                                val trip = tripState.value
                                trip.lowerRangeNumTravelers = lowerRangeNumTravelers.value
                                trip.higherRangeNumTravelers = higherRangeNumTravelers.value
                                trip.dateOfTrip = dateText
                                trip.timeOfTrip = timeText
                                trip.otherDetails = detailsText
                                tripState.value = trip
                                onProceedClicked()
                            }
                        }
                    },
                    contentPadding = PaddingValues(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Gray)
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
                showInvalidRangeMessage,
                stringResource(R.string.invalid_range)
            )
            BuildMessage(showInvalidDateMessage, stringResource(R.string.invalid_date))
            BuildMessage(showInvalidTimeMessage, stringResource(R.string.invalid_time))
        }
    }
}

@Composable
fun NumberOfTravelersSection(
    lowerRangeNumTravelers: MutableState<Int>,
    higherRangeNumTravelers: MutableState<Int>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.num_of_travelers),
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Row {
            Icon(
                Icons.Outlined.Group,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterVertically),
                contentDescription = stringResource(R.string.travelers_icon_description)
            )
            NumberPicker(
                state = lowerRangeNumTravelers,
                modifier = Modifier.padding(start = 13.dp),
                range = 1..10,
            )
            Text(
                text = stringResource(R.string.to),
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .align(Alignment.Bottom),
                fontSize = 22.sp
            )
            NumberPicker(
                state = higherRangeNumTravelers,
                range = 1..10,
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DateOfTripSection(
    dateText: String,
    setDateText: (String) -> Unit,
    dateFormatter: SimpleDateFormat
) {
    val datePickerDialog = createDatePickerDialog(LocalContext.current, setDateText, dateFormatter)
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(top = 30.dp)
    ) {
        Text(
            text = stringResource(R.string.date_of_trip),
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Row {
            Icon(
                Icons.Outlined.CalendarToday,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(32.dp)
                    .align(Alignment.CenterVertically),
                contentDescription = stringResource(R.string.calendar_icon_description)
            )
            TextButton(
                modifier = Modifier.align(Alignment.Bottom),
                contentPadding = PaddingValues(
                    all = 0.dp
                ),
                onClick = { datePickerDialog.show() }) {
                Column {
                    if (dateText.isBlank()) {
                        Text(
                            stringResource(R.string.date_placeholder),
                            style = TextStyle(color = PlaceholderGray, fontSize = 22.sp),
                        )
                    } else {
                        Text(
                            dateText, style = TextStyle(color = Color.Black, fontSize = 22.sp),
                        )
                    }

                    Divider(
                        modifier = Modifier.padding(top = 4.dp),
                        color = Color.Black,
                        thickness = 2.dp
                    )
                }
            }
        }
    }
}

@Composable
fun TimeOfTripSection(
    timeText: String,
    setTimeText: (String) -> Unit,
    timeFormatter: SimpleDateFormat
) {
    val timePickerDialog = createTimePickerDialog(LocalContext.current, setTimeText, timeFormatter)

    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(top = 30.dp)
    ) {
        Text(
            text = stringResource(R.string.time_of_trip),
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Row {
            Icon(
                Icons.Outlined.Schedule,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(32.dp)
                    .align(Alignment.CenterVertically),
                contentDescription = stringResource(R.string.clock_icon_description)
            )
            TextButton(
                modifier = Modifier.align(Alignment.Bottom),
                contentPadding = PaddingValues(
                    all = 0.dp
                ),
                onClick = { timePickerDialog.show() }) {
                Column {
                    if (timeText.isBlank()) {
                        Text(
                            stringResource(R.string.time_template),
                            style = TextStyle(color = PlaceholderGray, fontSize = 22.sp),
                        )
                    } else {
                        Text(
                            timeText, style = TextStyle(color = Color.Black, fontSize = 22.sp),
                        )
                    }

                    Divider(
                        modifier = Modifier.padding(top = 4.dp),
                        color = Color.Black,
                        thickness = 2.dp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OtherDetailsSection(
    detailsText: String,
    setDetailsText: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        Text(
            text = stringResource(R.string.other_details),
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Row {
            Icon(
                painterResource(R.drawable.ic_details_icon),
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(32.dp)
                    .align(Alignment.CenterVertically),
                contentDescription = stringResource(R.string.details_icon_description)
            )
            DenseTextField(
                value = detailsText,
                setValue = setDetailsText,
                placeholderText = stringResource(R.string.enter_details),
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .fillMaxWidth()
            )
        }
    }
}
