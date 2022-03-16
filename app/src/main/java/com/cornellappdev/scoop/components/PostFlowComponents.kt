package com.cornellappdev.scoop.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.ui.theme.DarkGray
import com.cornellappdev.scoop.ui.theme.Gray
import com.cornellappdev.scoop.ui.theme.PlaceholderGray
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone.Companion.currentSystemDefault
import kotlinx.datetime.toLocalDateTime
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SecondPage(onProceedClicked: () -> Unit) {
    val dateFormatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    val timeFormatter = SimpleDateFormat("h:mm aa", Locale.getDefault())
    val (detailsText, setDetailsText) = rememberSaveable { mutableStateOf("") }
    val lowerRangeNumTravelers = rememberSaveable { mutableStateOf(1) }
    val higherRangeNumTravelers = rememberSaveable { mutableStateOf(1) }
    val (dateText, setDateText) = rememberSaveable { mutableStateOf("") }
    val (timeText, setTimeText) = rememberSaveable { mutableStateOf("") }
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

    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
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
                            timeText.isEmpty() || timeFormatter.format(Date()) > timeText -> {
                                showInvalidTimeMessage = true
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
                    colors = buttonColors(backgroundColor = Gray)
                ) {
                    Icon(
                        Icons.Outlined.ArrowForward,
                        contentDescription = stringResource(R.string.arrow_forward_description)
                    )
                }
            }
        }
    }

    BuildMessage(showInvalidRangeMessage, "Please check the range on the number of travelers!")
    BuildMessage(showInvalidDateMessage, "Please enter a valid date!")
    BuildMessage(showInvalidTimeMessage, "Please pick a time in the future!")
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
                contentDescription = stringResource(R.string.calendar_icon_descrption)
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
                            stringResource(R.string.date_template),
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

fun createDatePickerDialog(
    context: Context,
    setDateText: (String) -> Unit,
    dateFormatter: SimpleDateFormat
): DatePickerDialog {
    val currentMoment = Clock.System.now()
    val date: LocalDateTime =
        currentMoment.toLocalDateTime(currentSystemDefault())

    val datePickerDialog = DatePickerDialog(
        context,
        android.R.style.Theme_DeviceDefault_Dialog_Alert,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            setDateText(dateFormatter.format(cal.time))
        }, date.year, date.monthNumber, date.dayOfMonth
    )
    datePickerDialog.datePicker.minDate = currentMoment.toEpochMilliseconds()
    return datePickerDialog
}

fun createTimePickerDialog(
    context: Context,
    setTimeText: (String) -> Unit,
    timeFormatter: SimpleDateFormat
): TimePickerDialog {
    val currentMoment = Clock.System.now()
    val date: LocalDateTime =
        currentMoment.toLocalDateTime(currentSystemDefault())

    val timePickerDialog = TimePickerDialog(
        context,
        android.R.style.Theme_DeviceDefault_Dialog_Alert,
        { _: TimePicker, hourOfDay: Int, minute: Int ->
            val cal = Calendar.getInstance()
            cal[Calendar.HOUR_OF_DAY] = hourOfDay
            cal[Calendar.MINUTE] = minute
            setTimeText(timeFormatter.format(cal.time))
        }, date.hour, date.minute, false
    )
    return timePickerDialog
}

/**
 * Builds composable to display the given message to the user.
 */
@Composable
fun BuildMessage(showMessage: Boolean, message: String) {
    AnimatedVisibility(
        visible = showMessage,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.wrapContentSize(),
                    shape = RoundedCornerShape(20.dp),
                    backgroundColor = DarkGray,
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 45.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = message,
                            style = TextStyle(color = Color.Black, fontSize = 22.sp),
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}