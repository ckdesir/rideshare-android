package com.cornellappdev.scoop.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.ui.theme.DarkGray
import com.cornellappdev.scoop.ui.theme.Gray
import com.cornellappdev.scoop.ui.theme.Green
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, com.google.accompanist.pager.ExperimentalPagerApi::class)
@Composable
fun RightArrow(
    nextFunction: suspend () -> Unit,
    isComplete : Boolean = false,
) {
    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .width(95.dp)
            .height(50.dp),
        shape = RoundedCornerShape(30.dp),
        enabled = isComplete,
        backgroundColor = if (isComplete) Green else Gray,
        onClick = {
            scope.launch {
                nextFunction()
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Next",
                modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight.Bold,
                color = if (isComplete) Color.Black else DarkGray
            )
        }
    }
}
