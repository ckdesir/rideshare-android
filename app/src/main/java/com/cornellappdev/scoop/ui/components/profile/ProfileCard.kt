package com.cornellappdev.scoop.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.Icecream
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.User
import com.cornellappdev.scoop.ui.components.general.UnderlinedEditText
import com.cornellappdev.scoop.ui.theme.Green

@Composable
fun ProfileCard(
    userState: MutableState<User>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(30.dp))
            .background(color = Color.White)
    ) {
        val editEnabled = remember { mutableStateOf(false) }

        val (nameText, setNameText) = rememberSaveable {
            mutableStateOf(
                (userState.value.firstName ?: "Name") + " " +
                        (userState.value.lastName ?: "Here")
            )
        }
        val (pronounsText, setPronounsText) = rememberSaveable {
            mutableStateOf(
                userState.value.pronouns ?: "(they/them)"
            )
        }
        val (gradeText, setGradeText) = rememberSaveable {
            mutableStateOf(
                userState.value.grade ?: "Junior"
            )
        }
        val (hometownText, setHometownText) = rememberSaveable {
            mutableStateOf(
                userState.value.hometown ?: "Ithaca, NY"
            )
        }
        val talkativity = rememberSaveable {
            mutableStateOf(
                userState.value.talkativity
            )
        }
        val musicAffinity = rememberSaveable {
            mutableStateOf(
                userState.value.musicAffinity
            )
        }
        val (songText, setSongText) = rememberSaveable {
            mutableStateOf(
                userState.value.song ?: "Dunno by Mac Miller"
            )
        }
        val (snackText, setSnackText) = rememberSaveable {
            mutableStateOf(
                userState.value.snack ?: "Peanut M&M's"
            )
        }
        val (stopText, setStopText) = rememberSaveable {
            mutableStateOf(
                userState.value.stop ?: "Sheetz"
            )
        }

        // Replace with IconButton
        IconButton(
            onClick = { editEnabled.value = !editEnabled.value },
            modifier = Modifier
                .background(Color.White)
                .align(Alignment.End)
                .padding(top = 8.dp, end = 10.dp)
                .size(40.dp)
        ) {
            Icon(
                painter = if (!editEnabled.value) painterResource(R.drawable.ic_edit_square_48px)
                else painterResource(R.drawable.ic_check_48px),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.Transparent),
                tint = Green
            )
        }
        Spacer(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth()
        )
        UnderlinedEditText(
            value = nameText,
            setValue = setNameText,
            placeholderText = "",
            enabled = editEnabled.value,
            modifier = Modifier.align(CenterHorizontally),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 30.sp,
                textAlign = TextAlign.Start
            ),
            unfocusedIndicatorColor = (
                    if (editEnabled.value) Color.Black else Color.White
                    )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            UnderlinedEditText(
                value = pronounsText,
                setValue = setPronounsText,
                placeholderText = "",
                enabled = editEnabled.value,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                ),
                unfocusedIndicatorColor = (
                        if (editEnabled.value) Color.Black
                        else Color.White)
            )
            Text(
                " | ",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                )
            )
            UnderlinedEditText(
                value = gradeText,
                setValue = setGradeText,
                placeholderText = "",
                enabled = editEnabled.value,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                ),
                unfocusedIndicatorColor = (
                        if (editEnabled.value) Color.Black
                        else Color.White)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Hometown: ",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                )
            )
            UnderlinedEditText(
                value = hometownText,
                setValue = setHometownText,
                placeholderText = "",
                enabled = editEnabled.value,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                ),
                unfocusedIndicatorColor = (
                        if (editEnabled.value) Color.Black
                        else Color.White)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
        )
        Text(
            "Traveling preferences",
            modifier = Modifier.padding(start = 29.dp),
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 29.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Column(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Quiet",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start
                        )
                    )
                    Text(
                        "Talkative",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start
                        )
                    )
                }
                ProfileSlider(
                    sliderPosition = talkativity
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "No music",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start
                        )
                    )
                    Text(
                        "Music",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start
                        )
                    )
                }
                ProfileSlider(
                    sliderPosition = musicAffinity
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
        Text(
            "Roadtrip Favorites",
            modifier = Modifier.padding(start = 29.dp),
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 29.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(vertical = 20.dp)
            ) {
                // Row for favorite song
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Outlined.MusicNote,
                        contentDescription = null
                    )
                    Text(
                        "Song: ",
                        modifier = Modifier.padding(start = 15.dp),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start
                        )
                    )
                    UnderlinedEditText(
                        value = songText,
                        setValue = setSongText,
                        placeholderText = "",
                        enabled = editEnabled.value,
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start
                        ),
                        unfocusedIndicatorColor = (
                                if (editEnabled.value) Color.Black
                                else Color.White)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )
                // Row for favorite snack
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Icecream,
                        contentDescription = null
                    )
                    Text(
                        "Snack: ",
                        modifier = Modifier.padding(start = 15.dp),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start
                        )
                    )
                    UnderlinedEditText(
                        value = snackText,
                        setValue = setSnackText,
                        placeholderText = "",
                        enabled = editEnabled.value,
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start
                        ),
                        unfocusedIndicatorColor = (
                                if (editEnabled.value) Color.Black
                                else Color.White)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )
                // Row for favorite stop
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Fastfood,
                        contentDescription = null
                    )
                    Text(
                        "Stop: ",
                        modifier = Modifier.padding(start = 15.dp),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start
                        )
                    )
                    UnderlinedEditText(
                        value = stopText,
                        setValue = setStopText,
                        placeholderText = "",
                        enabled = editEnabled.value,
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start
                        ),
                        unfocusedIndicatorColor = (
                                if (editEnabled.value) Color.Black
                                else Color.White)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileSlider(sliderPosition: MutableState<Float>) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(
            start = 15.dp,
            end = 15.dp
        )
    ) {
        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = sliderPosition.value,
            onValueChange = {
                sliderPosition.value = it
            },
            colors = customSliderColors()
        )
    }
}

@Composable
private fun customSliderColors(): SliderColors = SliderDefaults.colors(
    activeTickColor = Color.Transparent,
    inactiveTickColor = Color.Transparent,
    inactiveTrackColor = Color.Black,
    activeTrackColor = Color.Black,
    thumbColor = Color.Black
)
