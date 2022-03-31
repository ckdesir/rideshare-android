package com.cornellappdev.scoop.ui.components.general

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.ui.theme.PlaceholderGray

/**
 * Creates a Dense TextField, e.g. a TextField with no internal padding.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DenseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    singleLine: Boolean = true,
) {
    BasicTextField(
        value = value,
        modifier = modifier
            .indicatorLine(
                enabled = enabled,
                isError = false,
                interactionSource = interactionSource,
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black
                ),
                focusedIndicatorLineThickness = 2.dp,
                unfocusedIndicatorLineThickness = 2.dp
            )
            .height(32.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 22.sp),
        onValueChange = onValueChange,
        interactionSource = interactionSource,
        singleLine = singleLine
    ) { innerTextField ->
        TextFieldDefaults.TextFieldDecorationBox(
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Black.copy(alpha = ContentAlpha.high)
            ),
            enabled = enabled,
            interactionSource = interactionSource,
            visualTransformation = VisualTransformation.None,
            placeholder = {
                Text(
                    text = placeholderText,
                    style = TextStyle(color = PlaceholderGray, fontSize = 22.sp),
                )
            },
            value = value,
            innerTextField = innerTextField,
            singleLine = singleLine,
            contentPadding = PaddingValues(0.dp),
        )
    }
}