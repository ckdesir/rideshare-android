package com.cornellappdev.scoop.ui.components.general

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.ui.theme.PlaceholderGray


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Spinner(
    selectedOption: MutableState<String>,
    options: List<String>,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(R.string.select),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onSelectedOption: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = modifier
            .indicatorLine(
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            val text = selectedOption.value.ifBlank { placeholder }
            Text(
                text = text,
                style = TextStyle(
                    color = decideColor(text, placeholder),
                    fontSize = 22.sp
                ),
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
                        selectedOption.value = selectionOption
                        onSelectedOption(selectionOption)
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

fun decideColor(text: String, placeholder: String): Color {
    return if (text == placeholder) PlaceholderGray
    else Color.Black
}