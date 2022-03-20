package com.cornellappdev.scoop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R

@Composable
fun PostingScreen0(goToNext : () -> Unit) {
    Column {
        TextFields()
        Button(
            onClick = goToNext,
            modifier = Modifier.padding(start = 120.dp),
            colors = buttonColors(
                backgroundColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "arrow left"
            )
        }
    }
}

@Preview
@Composable
fun TextFields() {
    Column(
        modifier = Modifier.background(color = Color.White)
            .padding(start = 40.dp, end = 37.dp)
    ) {
        Text(
            text = "Method of Transportation",
            modifier = Modifier.padding(end = 21.dp)
                .fillMaxWidth(),
            fontSize = 22.sp
        )
        Row {
            DenseTextField("", {}, "select")
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier.size(20.dp)
                    .background(Color.White)
                    .align(alignment = Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "drop down menu",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
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
                    .padding(end = 15.dp)
                    .size(25.dp)
            )
            DenseTextField(
                "", {},
                "departure location..."
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        Row {
            Icon(
                imageVector = Icons.Outlined.Place,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(25.dp)
            )
            DenseTextField(
                "", {},
                "arrival location..."
            )
        }
    }
}