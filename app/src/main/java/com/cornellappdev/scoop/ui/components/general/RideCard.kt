package com.cornellappdev.scoop.ui.components.general

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.models.Trip
import com.cornellappdev.scoop.ui.theme.PlaceholderGray

@Composable
fun RideCard(
    trip: Trip
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
                    trip.methodOfTransportation?.let { transportation ->
                        Text(
                            text = "Michelle's $transportation",
                            style = TextStyle(color = Color.Black, fontSize = 22.sp)
                        )
                    }
                }

                Card(
                    modifier = Modifier.wrapContentSize(),
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = PlaceholderGray
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 13.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        trip.dateOfTrip?.let { date ->
                            Text(
                                text = date,
                                style = TextStyle(color = Color.Black, fontSize = 22.sp)
                            )
                        }
                    }
                }
            }

            Column(modifier = Modifier.padding(top = 8.dp)) {
                Row {
                    Icon(
                        Icons.Outlined.NearMe,
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = stringResource(R.string.details_icon_description)
                    )
                    trip.departureLocation?.let { departureLocation ->
                        Text(
                            departureLocation,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            style = TextStyle(color = Color.Black, fontSize = 18.sp),
                        )
                    }
                }

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

                Row {
                    Icon(
                        Icons.Outlined.Place,
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = stringResource(R.string.details_icon_description)
                    )
                    trip.arrivalLocation?.let { arrivalLocation ->
                        Text(
                            arrivalLocation,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            style = TextStyle(color = Color.Black, fontSize = 18.sp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowRideCard() {
    RideCard(
        Trip(
            "Taxi",
            dateOfTrip = "Mar 3",
            departureLocation = "Ithaca, NY",
            arrivalLocation = "New York, NY"
        )
    )
}