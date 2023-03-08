package com.cornellappdev.scoop.ui.components.search

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.data.models.Search
import com.cornellappdev.scoop.ui.components.general.CityPicker
import com.cornellappdev.scoop.ui.components.post.createDatePickerDialog
import java.text.SimpleDateFormat
import java.util.*

/**
 * Composable that displays information about the current state of search of the user.
 *
 * Additionally, allows users to edit the given fields of the search.
 *
 * If there is a filter applied prior to an edited search, the results of the edited search will
 * be filtered and returned in the callback of [onSearchCompleted]. Once editing mode is turned
 * on in the SearchCard, the manager of this composable is responsible for stopping it (this composable
 * has no icons to switch editing mode off).
 *
 * @param search State that represents the current state of search of the user
 * @param filter State that represents the current filter applied to the results of search
 * @param isEditing State that represents the current mode of the [SearchCard]
 * @param onSearchCompleted Callback that returns the results of the edited search back to the caller
 */
@Composable
fun SearchCard(
    search: MutableState<Search>,
    filter: MutableState<String?>,
    isEditing: MutableState<Boolean>,
    onSearchCompleted: (List<Ride>) -> Unit,
) {
    // CityPicker requires MutableStates for it's values but the Search model does
    // not have MutableStates for it's fields, so we must convert them and update the
    // search state in the callback of CityPicker.
    val departureLocation = remember { mutableStateOf(search.value.departureLocation!!) }
    val arrivalLocation = remember { mutableStateOf(search.value.arrivalLocation!!) }
    val departureDate = remember { mutableStateOf(search.value.departureDate!!) }

    val dateFormatter =
        SimpleDateFormat(stringResource(R.string.month_name_day_year_format), Locale.US)
    val datePickerDialog = createDatePickerDialog(
        LocalContext.current,
        { newDate ->
            if (departureDate.value != newDate) {
                search.value.departureDate = newDate
                departureDate.value = newDate

                // Query backend to get results with given date, filter if there's a filter
                onSearchCompleted(listOf())
            }
        }, dateFormatter
    )
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.White,
            border = BorderStroke(1.dp, Color.Black)
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
                                .padding(end = 12.dp)
                                .size(32.dp)
                                .align(Alignment.CenterVertically),
                            contentDescription = stringResource(R.string.details_icon_description)
                        )
                        CityPicker(
                            cityState = departureLocation,
                            modifier = Modifier.apply {
                                if (isEditing.value) {
                                    align(Alignment.Bottom)
                                } else {
                                    align(Alignment.CenterVertically)
                                }
                            },
                            icon = Icons.Outlined.NearMe, // TODO: change
                            placeholder = "", // CityPicker should never be empty.
                            enabled = isEditing.value,
                            disabledTextStyle = MaterialTheme.typography.subtitle1,
                            disableDivider = !isEditing.value,
                            onCityChanged = { it, _ ->
                                if (search.value.departureLocation != it) {
                                    search.value.departureLocation = it

                                    // TODO: Networking for searching for rides should be inserted here and passed into callback.
                                    onSearchCompleted(listOf())
                                }
                            }
                        )
                    }

                    Icon(
                        painterResource(R.drawable.ic_details_icon),
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { isEditing.value = !isEditing.value }
                            .align(Alignment.CenterVertically),
                        contentDescription = stringResource(R.string.details_icon_description)
                    )
                }

                if (!isEditing.value) {
                    Canvas(
                        Modifier
                            .padding(start = 16.dp)
                            .height(17.dp)
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

                Row(modifier = Modifier.padding(top = if (isEditing.value) 17.dp else 0.dp)) {
                    Icon(
                        Icons.Outlined.Place,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = stringResource(R.string.details_icon_description)
                    )
                    CityPicker(
                        cityState = arrivalLocation,
                        modifier = Modifier.apply {
                            if (isEditing.value) {
                                align(Alignment.Bottom)
                            } else {
                                align(Alignment.CenterVertically)
                            }
                        },
                        icon = Icons.Outlined.NearMe, // TODO: change
                        placeholder = "",
                        enabled = isEditing.value,
                        disabledTextStyle = MaterialTheme.typography.subtitle1,
                        disableDivider = !isEditing.value,
                        onCityChanged = { it, _ ->
                            if (search.value.arrivalLocation != it) {
                                search.value.arrivalLocation = it

                                /** TODO: Networking for searching for rides should be inserted here and passed into callback. */
                                onSearchCompleted(listOf())
                            }
                        }
                    )
                }

                Row(modifier = Modifier.padding(top = if (isEditing.value) 17.dp else 0.dp)) {
                    Icon(
                        Icons.Outlined.CalendarToday,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = stringResource(R.string.calendar_icon_description)
                    )
                    TextButton(
                        enabled = isEditing.value,
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
                        onClick = {
                            /** TODO: Change with custom calendar view if made eventually. */
                            datePickerDialog.show()
                        }) {
                        Column {
                            Text(
                                departureDate.value,
                                style = if (isEditing.value) MaterialTheme.typography.h5 else MaterialTheme.typography.subtitle1
                            )
                            if (isEditing.value) {
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
        }
    }
}