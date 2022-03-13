package com.cornellappdev.scoop.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
import com.cornellappdev.scoop.ui.theme.Gray
import com.cornellappdev.scoop.ui.theme.PlaceholderGray
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SecondPage(onProceedClicked: Unit) {
    val (detailsText, setDetailsText) = rememberSaveable { mutableStateOf("") }
    val lowerRangeNumTravelers = rememberSaveable { mutableStateOf(1) }
    val higherRangeNumTravelers = rememberSaveable { mutableStateOf(1) }

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
            DateOfTripSection()
            TimeOfTripSection()
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
                    onClick = {},
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
fun DateOfTripSection() {
    val (dateText, setDateText) = rememberSaveable { mutableStateOf("") }
    val datePickerDialog = createDatePickerDialog(LocalContext.current, setDateText)
    Column(
        modifier = Modifier
            .width(182.dp)
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
fun TimeOfTripSection() {
    val (timeText, setTimeText) = rememberSaveable { mutableStateOf("") }
    val timePickerDialog = createTimePickerDialog(LocalContext.current, setTimeText)

    Column(
        modifier = Modifier
            .width(182.dp)
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

fun createDatePickerDialog(context: Context, setDateText: (String) -> Unit): DatePickerDialog {
    val pattern = "MM/dd/yyyy"
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    val currentMoment = Clock.System.now()
    val date: LocalDateTime =
        currentMoment.toLocalDateTime(kotlinx.datetime.TimeZone.Companion.currentSystemDefault())

    val datePickerDialog = DatePickerDialog(
        context,
        android.R.style.Theme_DeviceDefault_Dialog_Alert,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            setDateText(simpleDateFormat.format(cal.time))
        }, date.year, date.monthNumber, date.dayOfMonth
    )
    datePickerDialog.datePicker.minDate = currentMoment.toEpochMilliseconds()
    return datePickerDialog
}

fun createTimePickerDialog(context: Context, setTimeText: (String) -> Unit): TimePickerDialog {
    val pattern = "h:mm aa"
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    val currentMoment = Clock.System.now()
    val date: LocalDateTime =
        currentMoment.toLocalDateTime(kotlinx.datetime.TimeZone.Companion.currentSystemDefault())

    val timePickerDialog = TimePickerDialog(
        context,
        android.R.style.Theme_DeviceDefault_Dialog_Alert,
        { _: TimePicker, hourOfDay: Int, minute: Int ->
            val cal = Calendar.getInstance()
            cal[Calendar.HOUR_OF_DAY] = hourOfDay
            cal[Calendar.MINUTE] = minute
            setTimeText(simpleDateFormat.format(cal.time))
        }, date.hour, date.minute, false
    )
    return timePickerDialog
}