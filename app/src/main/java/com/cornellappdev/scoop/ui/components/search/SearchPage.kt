package com.cornellappdev.scoop.ui.components.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.models.Search
import com.cornellappdev.scoop.ui.components.general.CityPicker
import com.cornellappdev.scoop.ui.theme.DarkGreen
import com.cornellappdev.scoop.ui.theme.PlaceholderGray

@Composable
fun SearchPage(onProceedClicked: () -> Unit, searchState: MutableState<Search>) {
    val departureLocation =
        rememberSaveable { mutableStateOf((searchState.value.departureLocation ?: "")) }
    val arrivalLocation =
        rememberSaveable { mutableStateOf((searchState.value.arrivalLocation ?: "")) }
    val departureDate = rememberSaveable { mutableStateOf((searchState.value.departureDate ?: "")) }
    val proceedEnabled = searchState.value.arrivalLocation?.isNotEmpty() == true &&
            searchState.value.departureLocation?.isNotEmpty() == true

    Column(
        modifier = Modifier
            .padding(horizontal = 17.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Row(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = stringResource(R.string.find_trips),
                style = TextStyle(fontSize = 30.sp),
                modifier = Modifier.padding(top = 35.dp, end = 8.dp)
            )
            Divider(
                modifier = Modifier
                    .width(116.dp)
                    .align(Alignment.Bottom)
                    .padding(bottom = 5.dp),
                color = Color.Black,
                thickness = 1.dp
            )
        }

        Canvas(
            modifier = Modifier
                .wrapContentHeight()
                .padding(start = 79.dp, top = 8.dp)
                .width(206.dp)
        ) {
            drawLine(
                color = DarkGreen,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(16f, 8f), 8f),
                strokeWidth = 2f
            )
        }

        Card(
            modifier = Modifier
                .padding(top = 16.75.dp, bottom = 33.dp)
                .fillMaxWidth(),
            backgroundColor = Color.White,
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 25.dp, end = 35.dp, top = 47.dp, bottom = 47.dp),
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.NearMe,
                        contentDescription = stringResource(R.string.departure_location),
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(36.dp)
                            .align(Alignment.CenterVertically)
                    )
                    CityPicker(
                        cityState = departureLocation,
                        placeholder = stringResource(R.string.departure_location),
                        placeholderColor = PlaceholderGray,
                        modifier = Modifier.align(Alignment.Bottom)
                    ) {
                        searchState.value = searchState.value.copy(departureLocation = it)
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Place,
                        contentDescription = stringResource(R.string.arrival_location),
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(36.dp)
                            .align(Alignment.CenterVertically)
                    )
                    CityPicker(
                        cityState = arrivalLocation,
                        placeholder = stringResource(R.string.arrival_location),
                        placeholderColor = PlaceholderGray,
                        modifier = Modifier.align(Alignment.Bottom)
                    ) {
                        searchState.value = searchState.value.copy(arrivalLocation = it)
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CalendarToday,
                        contentDescription = stringResource(R.string.calendar_icon_description),
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(36.dp)
                            .align(Alignment.CenterVertically)
                    )

                    val isDepartureDateEmpty = departureDate.value.isEmpty()
                    TextButton(
                        onClick = { /*TODO*/ },
                        contentPadding = PaddingValues(0.dp),
                    ) {
                        Column {
                            Text(
                                text = if (isDepartureDateEmpty) stringResource(R.string.departure_date) else departureDate.value,
                                style = TextStyle(
                                    color = if (isDepartureDateEmpty) PlaceholderGray else Color.Black,
                                    fontSize = 22.sp
                                )
                            )
                            Divider(color = Color.Black, thickness = 2.dp)
                        }
                    }
                }
            }
        }

        TextButton(
            enabled = proceedEnabled,
            onClick = onProceedClicked,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = DarkGreen,
                disabledBackgroundColor = DarkGreen.copy(0.40f)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(69.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = stringResource(R.string.find_trips),
                style = TextStyle(color = Color.White, fontSize = 24.sp)
            )
        }
    }
}
