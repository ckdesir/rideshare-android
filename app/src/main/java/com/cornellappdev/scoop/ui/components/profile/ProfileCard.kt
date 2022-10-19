package com.cornellappdev.scoop.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.R

@Composable
fun ProfileCard() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var editEnabled = remember { mutableStateOf(false) }

        Button(
            onClick = { editEnabled.value = !editEnabled.value },
            modifier = Modifier
                .size(40.dp)
                .background(Color.Transparent),
            contentPadding = PaddingValues(
                start = 10.dp,
                top = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            )
        ) {
            Icon(
                painter = if (!editEnabled.value) painterResource(R.drawable.ic_edit_square_48px)
                else painterResource(R.drawable.ic_check_48px),
                contentDescription = null,
                modifier = Modifier
                    .size(ButtonDefaults.IconSize)
                    .background(Color.Transparent)
            )
        }
    }
}