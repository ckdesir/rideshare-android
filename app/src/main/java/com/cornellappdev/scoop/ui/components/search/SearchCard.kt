package com.cornellappdev.scoop.ui.components.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.ui.viewmodel.SearchScreenViewModel

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
 * @param searchScreenViewModel State that represents the current state of search of the user
 * @param filter State that represents the current filter applied to the results of search
 * @param isEditing State that represents the current mode of the [SearchCard]
 * @param onSearchCompleted Callback that returns the results of the edited search back to the caller
 */
@Composable
fun SearchCard(
    searchScreenViewModel: SearchScreenViewModel,
    filter: MutableState<String?>,
    isEditing: MutableState<Boolean>,
    onSearchCompleted: (List<Ride>) -> Unit,
) {
    // CityPicker requires MutableStates for its values but the Search model does
    // not have MutableStates for its fields, so we must convert them and update the
    // search state in the callback of CityPicker.

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
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Filled.NearMe,
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .size(32.dp)
                                .align(Alignment.CenterVertically),
                            contentDescription = stringResource(R.string.details_icon_description)
                        )
                        Text(
                            text = searchScreenViewModel.search.departureLocationName ?: "null",
                            style = MaterialTheme.typography.subtitle1
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
                Canvas(
                    Modifier
                        .padding(start = 16.dp)
                        .height(10.dp)
                ) {
                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 8f), 0f),
                        strokeWidth = 3f
                    )
                }
                Row(
                    modifier = Modifier.padding(top = if (isEditing.value) 17.dp else 0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Place,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = stringResource(R.string.details_icon_description)
                    )
                    Text(
                        text = searchScreenViewModel.search.arrivalLocationName ?: "null",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Row(
                    modifier = Modifier.padding(top = if (isEditing.value) 17.dp else 0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.CalendarToday,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = stringResource(R.string.calendar_icon_description)
                    )
                    Text(
                        text = searchScreenViewModel.search.departureDate ?: "null",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
        }
    }
}