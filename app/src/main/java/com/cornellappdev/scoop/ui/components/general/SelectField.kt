package com.cornellappdev.scoop.ui.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.ui.theme.PlaceholderGray


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectField(
    options: List<String>,
    input: MutableState<String>,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("select") }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = modifier.indicatorLine(
            enabled = enabled,
            isError = false,
            interactionSource = interactionSource,
            colors = textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black
            ),
            focusedIndicatorLineThickness = 2.dp,
            unfocusedIndicatorLineThickness = 2.dp
        )
            .height(40.dp)
            .padding(0.dp)
            .fillMaxWidth()
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .height(40.dp)
        ) {
            Text(
                text = selectedOptionText,
                style = TextStyle(color = decideColor(selectedOptionText), fontSize = 22.sp),
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = (if (!expanded)
                    Icons.Outlined.ExpandMore
                else Icons.Outlined.ExpandLess),
                contentDescription = "Expand more"
            )
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        input.value = selectedOptionText
                        expanded = false
                    }
                ) {
                    Text(
                        text = selectionOption,
                        style = TextStyle(color = Color.Black, fontSize = 22.sp),
                    )
                }
            }
        }
    }
}

fun decideColor(text : String) : Color {
    return if (text == "select") PlaceholderGray;
    else Color.Black;
}