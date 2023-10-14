package com.cornellappdev.scoop.ui.components.post

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.ui.components.general.BuildMessage
import com.cornellappdev.scoop.ui.components.general.DenseTextField
import com.cornellappdev.scoop.ui.components.general.UnderlinedEditText
import com.cornellappdev.scoop.ui.theme.Gray
import com.cornellappdev.scoop.ui.theme.PlaceholderGray
import com.cornellappdev.scoop.ui.viewmodel.PostScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * This composable creates the second page of the posting a ride flow which handles date and time
 * as well as minimum and maximum travelers.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SecondPage(onProceedClicked: () -> Unit, postScreenViewModel: PostScreenViewModel) {
    val dateFormatter =
        SimpleDateFormat(stringResource(R.string.date_format), Locale.getDefault())
    val timeFormatter =
        SimpleDateFormat(stringResource(R.string.time_format), Locale.getDefault())
    val dateAndTimeFormatter =
        SimpleDateFormat(stringResource(R.string.date_time_format), Locale.getDefault())
    val (detailsText, setDetailsText) = rememberSaveable {
        mutableStateOf(postScreenViewModel.ride.description.orEmpty())
    }
    val (minTravelers, setMinTravelers) =
        rememberSaveable { mutableStateOf((postScreenViewModel.ride.minTravelers.toString())) }
    val (maxTravelers, setMaxTravelers) =
        rememberSaveable { mutableStateOf((postScreenViewModel.ride.maxTravelers.toString())) }
    val (dateText, setDateText) = rememberSaveable {
        mutableStateOf(postScreenViewModel.ride.datetime?.substringBefore(' ').orEmpty())
    }
    val (timeText, setTimeText) = rememberSaveable {
        mutableStateOf(postScreenViewModel.ride.datetime?.substringAfter(' ').orEmpty())
    }
    // TODO: merge date and time and figure out how to separate if design wants that
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
                .padding(top = 30.dp)
        ) {

            DateOfTripSection(dateText, setDateText, dateFormatter)
            TimeOfTripSection(timeText, setTimeText, timeFormatter)
            NumberOfTravelersSection(
                minTravelers, setMinTravelers, maxTravelers, setMaxTravelers
            )
            OtherDetailsSection(detailsText, setDetailsText)

            Column(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 18.dp)
                    .wrapContentSize().weight(1f)
            ) {
                Button(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 50.dp)
                        .width(86.dp)
                        .height(51.dp),
                    shape = RoundedCornerShape(26.dp),
                    enabled = proceedEnabled,

                    onClick = {
                        var numMinTravelers = 0
                        var numMaxTravelers = 10
                        try {
                            numMinTravelers = Integer.valueOf(minTravelers)
                            numMaxTravelers = Integer.valueOf(maxTravelers)
                        } catch (nfe: NumberFormatException) {
                            showInvalidRangeMessage = true
                            proceedEnabled = false
                            coroutineScope.launch {
                                disableMessage()
                            }
                        }
                        when {
                            numMinTravelers > numMaxTravelers -> {
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
                                // Updates view model with details collected on SecondPage
                                postScreenViewModel.setMinTravelers(numMinTravelers)
                                postScreenViewModel.setMaxTravelers(numMaxTravelers)
                                postScreenViewModel.setDatetime("$dateText $timeText")
                                postScreenViewModel.setDescription(detailsText)
                                onProceedClicked()
                            }
                        }
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
                        color = (if (proceedEnabled) Color.Black else Color(0xFF001E2D)),
                        fontWeight = FontWeight.Bold
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
    minTravelers: String, setMinTravelers: (String) -> Unit,
    maxTravelers: String, setMaxTravelers: (String) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 30.dp)) {
        Text(
            text = stringResource(R.string.num_of_travelers),
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row {
            DenseTextField(
                value = if (minTravelers == "null") "" else minTravelers,
                setValue = setMinTravelers,
                placeholderText = "Minimum",
                label = "Minimum",
                singleLine = true,
                modifier = Modifier.weight(1f),
                phoneNumber = true
            )
            Spacer(
                modifier = Modifier.width(15.dp)
            )
            DenseTextField(
                value = if (maxTravelers == "null") "" else maxTravelers,
                setValue = setMaxTravelers,
                placeholderText = "Maximum",
                label = "Maximum",
                singleLine = true,
                modifier = Modifier.weight(1f),
                phoneNumber = true
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
            .padding(top = 30.dp)
    ) {

        //DenseTextField and Icon with transparent clickable Box on top
        Box (modifier = Modifier.fillMaxWidth()) {
            Box(Modifier.fillMaxWidth()) {
                DenseTextField(
                    value = if (dateText == "null") "" else dateText,
                    setValue = setDateText,
                    placeholderText = stringResource(R.string.date_placeholder),
                    singleLine = true,
                    label = "Departure Date",
                    phoneNumber = true,
                )
            }
            Icon(
                Icons.Filled.CalendarToday,
                modifier = Modifier
                    .padding(end = 12.dp, top = 4.dp)
                    .size(28.dp)
                    .align(Alignment.CenterEnd),
                contentDescription = stringResource(R.string.calendar_icon_description)
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0f)
                    .clickable(onClick = { datePickerDialog.show()} ),
            )
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
            .padding(top = 30.dp)
    ) {
        // Box stacking a clickable Clock icon on top of a DenseTextField
        Box (modifier = Modifier.fillMaxWidth()) {
            Box(Modifier.fillMaxWidth()) {
                DenseTextField(
                    value = if (timeText == "null") "" else timeText,
                    setValue = setTimeText,
                    placeholderText = stringResource(R.string.time_template),
                    singleLine = true,
                    label = "Time of trip",
                )
            }
            Icon(
                Icons.Outlined.Schedule,
                modifier = Modifier
                    .padding(end = 12.dp, top = 4.dp)
                    .size(28.dp)
                    .align(Alignment.CenterEnd),
                contentDescription = stringResource(R.string.clock_icon_description)
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0f)
                    .clickable(onClick = { timePickerDialog.show()} ),
            )
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
        DenseTextField(
            value = if (detailsText == "null") "" else detailsText,
            setValue = setDetailsText,
            placeholderText = stringResource(R.string.other_details),
            singleLine = false,
            label = "Details",
            wrapText = true,
            maxLines = 3
        )
    }
}