package com.cornellappdev.scoop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.ui.graphics.Color.Companion.DarkGray

class Trips : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Preview
@Composable
fun MyApp() {
    Column {
        Row {
            ScoopIcon()
            Text(
                text = "scoop",
                modifier = Modifier.padding(20.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun ScoopIcon() {
    Icon(
        painter = painterResource(id = R.drawable.scoop_icon_foreground),
        contentDescription = "Scoop Icon",
    )
}