package com.cornellappdev.scoop.ui.components.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.ui.components.general.CityPicker
import com.cornellappdev.scoop.ui.components.general.DatePicker
import com.cornellappdev.scoop.ui.theme.PlaceholderGray

@Composable
fun SearchFirstPage() {
    val departureLocation = rememberSaveable { mutableStateOf("") }
    val arrivalLocation = rememberSaveable { mutableStateOf("") }
    val dateText = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.find_trips),
            style = TextStyle(color = Color.Black, fontSize = 24.sp),
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier.height(120.dp)
        )
        CityPicker(
            cityState = departureLocation,
            placeholder = "Departure location",
            placeholderColor = PlaceholderGray,
            icon = Icons.Filled.NearMe,
            disableDivider = true
        )
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        CityPicker(
            cityState = arrivalLocation,
            placeholder = "Arrival Location",
            placeholderColor = PlaceholderGray,
            icon = Icons.Filled.Place,
            disableDivider = true
        )
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        DatePicker(
            dateState = dateText,
            placeholder = stringResource(R.string.date_placeholder),
            icon = Icons.Filled.CalendarToday,
            disableDivider = true,
        ) {
            dateText.value = it
        }
        Spacer(
            modifier = Modifier.height(71.dp)
        )
    }
}