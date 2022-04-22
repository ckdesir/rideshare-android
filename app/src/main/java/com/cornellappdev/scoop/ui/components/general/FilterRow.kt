package com.cornellappdev.scoop.ui.components.general

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.rememberRipple
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
import com.cornellappdev.scoop.ui.theme.DarkGreen

/**
 * LazyRow of selectable filters.
 *
 * If a filter is unselected, the filter value is null.
 *
 * @param filter State that represents the current filter selected by the user
 * @param selectableFilters List of selectable filters
 * @param modifier Modifier to apply to FilterRow
 * @param selectedColor Color to apply to row if selected (if selected, the color of the text is white)
 * @param onFilterSelected Callback that is called when new filter is selected by the user
 */
@Composable
fun FilterRow(
    filter: MutableState<String?>,
    selectableFilters: List<String>,
    modifier: Modifier = Modifier,
    selectedColor: Color = DarkGreen,
    onFilterSelected: () -> Unit,
) {
    val selectedIndex = remember { mutableStateOf(-1) }

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        itemsIndexed(selectableFilters) { index, item ->
            val isSelected = selectedIndex.value == index
            val interactionSource = remember { MutableInteractionSource() }
            TextButton(
                onClick = {
                    if (isSelected) {
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
                modifier = Modifier.indication(
                    interactionSource, rememberRipple(
                        bounded = true,
                        radius = 60.dp,
                        color = if (isSelected) Color.Transparent else selectedColor
                    )
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = if (isSelected) selectedColor else Color.White),
                interactionSource = interactionSource,
                border = BorderStroke(1.dp, if (isSelected) Color.Transparent else Color.Black)
            ) {
                Box(
                    modifier = Modifier.padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        item,
                        style = TextStyle(
                            color = if (isSelected) Color.White else Color.Black,
                            fontSize = 20.sp
                        )
                    )
                }
            }
        }
    }
}