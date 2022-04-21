package com.cornellappdev.scoop.ui.components.general

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * LazyRow of selectable filters.
 *
 * @param[filter] State that represents the current filter selected by the user
 * @param[selectableFilters] List of selectable filters
 * @param[onFilterSelected] Callback that is called when new filter is selected by the user
 */
@Composable
fun FilterRow(
    filter: MutableState<String?>,
    selectableFilters: List<String>,
    modifier: Modifier = Modifier,
    onFilterSelected: () -> Unit,
) {
    val selectedIndex = remember { mutableStateOf(-1) }

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(selectableFilters) { index, item ->
            Card(
                modifier = Modifier.clickable {
                    if (selectedIndex.value == index) {
                        // Deselects the given filter if it's clicked again.
                        selectedIndex.value = -1
                        filter.value = null
                    } else {
                        selectedIndex.value = index
                        filter.value = item
                    }
                    onFilterSelected.invoke()
                },
                shape = RoundedCornerShape(50.dp),
                border = BorderStroke(2.dp, Color.Black),
                backgroundColor = if (index == selectedIndex.value) Color.Gray else Color.White
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        item,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp
                        )
                    )
                }
            }
        }
    }
}