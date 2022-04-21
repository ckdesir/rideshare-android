package com.cornellappdev.scoop.ui.components.general

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.models.Trip
import com.cornellappdev.scoop.ui.components.post.createDatePickerDialog
import java.text.SimpleDateFormat
import java.util.*

/**
 * Composable that displays information about the current state of search of the user.
 *
 * Additionally, allows users to edit the given fields of the search and filter.
 * The results of any new/filtered searches are returned in the callback [onSearchCompleted].
 *
 * @param[departureLocation] State that represents the departure location users want a trip from
 * @param[arrivalLocation] State that represents the arrival location users want a trip to
 * @param[date] State that represents the date users want a trip from
 * @param[onEditingChangeEvent] Callback that returns true if user is editing their search
 * @param[onSearchCompleted] Callback that returns the results of the edited search back to the caller
 */
@Composable
fun SearchFilterCard(
    departureLocation: MutableState<String>,
    arrivalLocation: MutableState<String>,
    date: MutableState<String>,
    onEditingChangeEvent: (Boolean) -> Unit,
    onSearchCompleted: (List<Trip>) -> Unit,
) {
    val isEditing = rememberSaveable { mutableStateOf(false) }
    val filter = rememberSaveable { mutableStateOf<String?>(null) }
    val dateFormatter =
        SimpleDateFormat(stringResource(R.string.month_name_day_year_format), Locale.US)
    val datePickerDialog = createDatePickerDialog(
        LocalContext.current,
        { newDate ->
            date.value = newDate
            // Query backend to get results with given date, filter if there's a filter
            onSearchCompleted(listOf())
        }, dateFormatter
    )

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Color.Gray
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Icon(
                        Icons.Outlined.NearMe,
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = stringResource(R.string.details_icon_description)
                    )
                    // CityPicker
                    Text(
                        departureLocation.value,
                        modifier = Modifier.apply {
                            if (isEditing.value) {
                                align(Alignment.Bottom)
                            } else {
                                align(Alignment.CenterVertically)
                            }
                        },
                        style = if (isEditing.value) MaterialTheme.typography.h5 else MaterialTheme.typography.subtitle1
                    )
                }

                if (!isEditing.value) {
                    Column(modifier = Modifier
                        .clickable {
                            isEditing.value = !isEditing.value
                            onEditingChangeEvent(isEditing.value)
                        }
                        .align(Alignment.CenterVertically)
                        .padding(end = 12.dp)) {
                        Icon(
                            painterResource(R.drawable.ic_details_icon),
                            modifier = Modifier
                                .size(32.dp),
                            contentDescription = stringResource(R.string.details_icon_description)
                        )
                    }
                }
            }

            if (!isEditing.value) {
                Canvas(
                    Modifier
                        .height(30.dp)
                        .wrapContentWidth()
                        .padding(top = 5.dp, bottom = 5.dp, start = 16.dp)
                ) {
                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 8f), 0f),
                        strokeWidth = 3f
                    )
                }
            }

            Row {
                Icon(
                    Icons.Outlined.Place,
                    modifier = Modifier.apply {
                        size(32.dp)
                        align(Alignment.CenterVertically)
                        if (isEditing.value) {
                            padding(end = 15.dp, top = 17.dp)
                        } else {
                            padding(end = 15.dp)
                        }
                    },
                    contentDescription = stringResource(R.string.details_icon_description)
                )
                // CityPicker
                Text(
                    arrivalLocation.value,
                    modifier = Modifier.apply {
                        if (isEditing.value) {
                            align(Alignment.Bottom)
                        } else {
                            align(Alignment.CenterVertically)
                        }
                    },
                    style = if (isEditing.value) MaterialTheme.typography.h5 else MaterialTheme.typography.subtitle1

                )
            }

            Row {
                Icon(
                    Icons.Outlined.CalendarToday,
                    modifier = Modifier
                        .padding(end = 12.dp, top = 17.dp)
                        .size(32.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = stringResource(R.string.calendar_icon_description)
                )
                TextButton(
                    modifier = Modifier.apply {
                        if (isEditing.value) {
                            align(Alignment.Bottom)
                        } else {
                            align(Alignment.CenterVertically)
                        }
                    },
                    contentPadding = PaddingValues(
                        all = 0.dp
                    ),
                    onClick = { datePickerDialog.show() }) {
                    Column {
                        if (isEditing.value) {
                            Text(
                                date.value,
                                style = TextStyle(color = Color.Black, fontSize = 22.sp),
                            )

                            Divider(
                                modifier = Modifier.padding(top = 4.dp),
                                color = Color.Black,
                                thickness = 2.dp
                            )
                        } else {
                            Text(
                                date.value,
                                style = TextStyle(color = Color.Black, fontSize = 18.sp),
                            )
                        }
                    }
                }
            }
        }
    }

    FilterRow(
        filter,
        stringArrayResource(id = R.array.methods_of_transportation).asList(),
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxSize()
    ) {
        // Query backend to get results with given data, filter with given filter if applicable.
        onSearchCompleted(listOf())
    }
}

@Preview(showBackground = true)
@Composable
fun ShowSearchCard() {
    SearchFilterCard(
        remember { mutableStateOf("Ithaca, NY") },
        remember { mutableStateOf("New York, NY") },
        remember { mutableStateOf("March 3, 2022") },
        {}
    ) {}
}