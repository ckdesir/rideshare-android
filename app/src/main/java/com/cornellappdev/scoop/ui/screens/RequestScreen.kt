package com.cornellappdev.scoop.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.ui.theme.DarkGreen

@Composable
fun RequestScreen(
    rideState: MutableState<Ride>
) {
   // TODO: No actual values just yet. Everything is just filler information.

    Column(
        Modifier.padding(30.dp, 10.dp),
        Arrangement.spacedBy(20.dp)
    ) {
        
        //TODO: add back functionality
        NavHeader(title = "Test's Drive", backFunction = {}, hasBackArrow = true)

        Spacer(modifier = Modifier.height(10.dp))

        //Profile Section
        Row(
        ) {

            Image(
                painter = painterResource(R.drawable.ic_profile_icon),
                contentDescription = "profile",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, Color.Black, CircleShape)   // add a border (optional)
            )

            Column(
                Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
            ) {
                
                //Name
                Text(text = "First Last")

                Row() {

                    Image(painter = painterResource(id = R.drawable.ic_baseline_email_24), contentDescription = "email icon")
                    Text(
                        modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp),
                        text = "appdevgmail.com")
                }
            }
        }

        Column() {

            Text(fontWeight = FontWeight.Bold, text = "TRANSPORTATION METHOD")
            Row() {

                Image(painter = painterResource(id = R.drawable.ic_scoopblackcar), contentDescription = "car icon")
                Text(
                    modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp),
                    text = "Student Driver")
            }
        }

        Column() {

            Text(fontWeight = FontWeight.Bold, text = "LOCATIONS")
            Row {
                Icon(
                    Icons.Outlined.NearMe,
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(24.dp)
                        .align(CenterVertically),
                    contentDescription = stringResource(R.string.details_icon_description)
                )
                rideState.value.departureLocation?.let { departureLocation ->
                    Text(
                        departureLocation,
                        modifier = Modifier.align(CenterVertically),
                        style = MaterialTheme.typography.body1,
                    )
                }
            }

            Canvas(
                Modifier
                    .height(10.dp)
                    .wrapContentWidth()
                    .padding(start = 12.dp)
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
                        .size(24.dp)
                        .align(CenterVertically),
                    contentDescription = stringResource(R.string.details_icon_description)
                )
                rideState.value.arrivalLocation?.let { arrivalLocation ->
                    Text(
                        arrivalLocation,
                        modifier = Modifier.align(CenterVertically),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }

        Column() {

            Text(fontWeight = FontWeight.Bold, text = "DEPARTURE DATE")
            Row() {
                Image(painter = painterResource(id = R.drawable.ic_baseline_calendar_today_24), contentDescription = "cal icon")
                Text(
                    modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp),
                    text = "Student Driver")
            }
        }

        Column() {

            Text(fontWeight = FontWeight.Bold, text = "NUMBER OF TRAVELERS")
            Row() {
                Text(text = "1 to 3 People")
            }
        }

        Column() {

            Text(fontWeight = FontWeight.Bold, text = "DETAILS")
            Row() {
                Text(text = "Depoarture time flexible, gas money ~$20, will drop off near times square")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .width(300.dp)
                .height(50.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 6.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(backgroundColor = DarkGreen)
        )
        {
            Text(text = "Request to Join", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
