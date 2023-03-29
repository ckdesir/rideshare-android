package com.cornellappdev.scoop.ui.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.ui.components.general.CityPicker
import com.cornellappdev.scoop.ui.components.general.DatePicker
import com.cornellappdev.scoop.ui.theme.Green
import com.cornellappdev.scoop.ui.theme.PlaceholderGray
import com.cornellappdev.scoop.ui.theme.typography
import com.cornellappdev.scoop.ui.viewmodel.SearchScreenViewModel

/**
 * Displays the first page of search including departure location, arrival location, and date
 */
@Composable
fun SearchFirstPage(
    onProceedClicked: () -> Unit,
    searchScreenViewModel: SearchScreenViewModel
) {
    val departureLocation = rememberSaveable { mutableStateOf("") }
    val arrivalLocation = rememberSaveable { mutableStateOf("") }
    val dateText = rememberSaveable { mutableStateOf("") }

    val proceedEnabled = rememberSaveable {
        mutableStateOf(
            searchScreenViewModel.search.departureLocationName != null
                    && searchScreenViewModel.search.arrivalLocationName != null
                    && searchScreenViewModel.search.departureDate != null
        )
    }
    val updateProceedEnabled = {
        proceedEnabled.value = searchScreenViewModel.search.departureLocationName != null
                && searchScreenViewModel.search.arrivalLocationName != null
                && searchScreenViewModel.search.departureDate != null
    }

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
            modifier = Modifier.height(150.dp)
        )
        CityPicker(
            cityState = departureLocation,
            placeholder = "Departure location",
            placeholderColor = PlaceholderGray,
            icon = Icons.Filled.NearMe,
            disableDivider = true
        ) { name, id ->
            searchScreenViewModel.setDepartureName(name)
            searchScreenViewModel.setDeparturePlaceId(id)
            updateProceedEnabled()
        }
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        CityPicker(
            cityState = arrivalLocation,
            placeholder = "Arrival Location",
            placeholderColor = PlaceholderGray,
            icon = Icons.Filled.Place,
            disableDivider = true
        ) { name, id ->
            searchScreenViewModel.setArrivalName(name)
            searchScreenViewModel.setArrivalPlaceId(id)
            updateProceedEnabled()
        }
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
            searchScreenViewModel.setDepartureDate(it)
            updateProceedEnabled()
        }
        Spacer(
            modifier = Modifier.height(71.dp)
        )
        TextButton(
            onClick = onProceedClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 8.dp)
                .background(Green, RoundedCornerShape(25.dp)),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Green,
                disabledBackgroundColor = Color(0xFFDBE5DF)
            ),
            enabled = proceedEnabled.value
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Find Trips",
                    style = typography.body1,
                    color = Color.White,
                    fontWeight = Bold,
                    modifier = Modifier.align(Center),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}