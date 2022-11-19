package com.cornellappdev.scoop.ui.components.post

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.ui.components.general.CityPicker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FirstPage(onProceedClicked: () -> Unit, rideState: MutableState<Ride>) {
    val (typeText, setTypeText) = rememberSaveable { mutableStateOf(rideState.value.type.orEmpty()) }
    val departureText = rememberSaveable {
        mutableStateOf(rideState.value.departureLocation.orEmpty())
    }
    val arrivalText = rememberSaveable {
        mutableStateOf(rideState.value.arrivalLocation.orEmpty())
    }
    var showInvalidTypeMessage by rememberSaveable { mutableStateOf(false) }
    var proceedEnabled by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    suspend fun disableMessage() {
        delay(3000L)
        when {
            showInvalidTypeMessage -> {
                showInvalidTypeMessage = false
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
                .padding(top = 240.dp)
        ) {
            TransportationSection(
                typeText,
                setTypeText,
                modifier = Modifier.onFocusChanged {
                    rideState.value.type = typeText
                }
            )
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            LocationSection(
                location = departureText,
                placeholder = "Departure location",
                Icons.Filled.NearMe,
                modifier = Modifier.onFocusChanged {
                    rideState.value.departureLocation = departureText.value
                }
            )
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            LocationSection(
                location = arrivalText,
                placeholder = "Arrival location",
                Icons.Filled.Place,
                modifier = Modifier.onFocusChanged {
                    rideState.value.arrivalLocation = arrivalText.value
                }
            )

            Column(
                modifier = Modifier
                    .align(Alignment.End)
                    .wrapContentSize()
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 118.dp)
                        .width(86.dp)
                        .height(51.dp),
                    shape = RoundedCornerShape(26.dp),
                    enabled = proceedEnabled,
                    onClick = {
                        when (typeText) {
                            null -> {
                                showInvalidTypeMessage = true
                                proceedEnabled = false
                                coroutineScope.launch {
                                    disableMessage()
                                }
                            }
                            else -> {
                                // Updates trip state with details collected on FirstPage
                                val ride = rideState.value
                                ride.type = typeText
                                rideState.value = ride
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
                        fontWeight = Bold
                    )
                }
            }
        }
    }
}

@Composable
fun TransportationSection(
    typeText: String,
    setTypeText: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf("Student driver", "Taxi")
    val selectedValue = remember { mutableStateOf(typeText) }
    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }

    Column {
        Text(
            "TRANSPORTATION METHOD",
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        items.forEach { item ->
            Row(
                verticalAlignment = CenterVertically,
                modifier = modifier
                    .selectable(
                        selected = isSelectedItem(item),
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = { setTypeText(item); selectedValue.value = item },
                        role = Role.RadioButton
                    )
                    .padding(14.dp)
            ) {
                RadioButton(
                    selected = isSelectedItem(item),
                    onClick = null
                )
                Spacer(
                    modifier = Modifier.width(14.dp)
                )
                Text(
                    text = item,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun LocationSection(
    location: MutableState<String>,
    placeholder: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                1.dp,
                Color.Gray,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            Icon(
                icon,
                null,
                modifier = Modifier
                    .padding(vertical = 15.dp)
            )
            Spacer(
                modifier = Modifier.width(5.dp)
            )
            CityPicker(
                cityState = location,
                placeholder = placeholder,
                disableDivider = true,
                placeholderColor = Color(0xFF001E2D),
                modifier = Modifier.align(CenterVertically)
            )
        }
    }
}