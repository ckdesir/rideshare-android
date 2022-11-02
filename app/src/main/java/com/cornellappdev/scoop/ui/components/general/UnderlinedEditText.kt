package com.cornellappdev.scoop.ui.components.general

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.ui.theme.PlaceholderGray

/**
 * Creates a underlined TextField, e.g. a TextField with no internal padding.
 *
 * @param value The text to be displayed
 * @param setValue The callback that is triggered when the input service updates the text.
 *      An updated text comes as a parameter of the callback.
 * @param placeholderText The placeholder to be displayed when the text field is in focus and the input text is empty
 * @param modifier Modifier to be applied to the [UnderlinedEditText]
 * @param interactionSource The InteractionSource of this text field.
 *      Helps to determine if the text field is in focus or not.
 * @param enabled Controls the enabled state of the button.
 *      When false, the text field will be neither editable nor focusable,
 *      the input of the text field will not be selectable.
 * @param singleLine When set to true, this text field becomes
 *      a single horizontally scrolling text field instead of wrapping onto multiple lines.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UnderlinedEditText(
    value: String,
    setValue: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    singleLine: Boolean = true,
    unfocusedIndicatorColor: Color = Color.Black,
    textStyle: TextStyle = TextStyle(
        color = Color.Black,
        fontSize = 22.sp,
        textAlign = TextAlign.Start
    )
) {
    BasicTextField(
        value = value,
        enabled = enabled,
        modifier = modifier
            .indicatorLine(
                enabled = enabled,
                isError = false,
                interactionSource = interactionSource,
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = unfocusedIndicatorColor
                ),
                focusedIndicatorLineThickness = 2.dp,
                unfocusedIndicatorLineThickness = 2.dp
            )
            .wrapContentHeight()
            .width(IntrinsicSize.Min),
        textStyle = textStyle,
        onValueChange = setValue,
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
                    style = TextStyle(
                        color = PlaceholderGray,
                        fontSize = textStyle.fontSize,
                        textAlign = TextAlign.Start
                    ),
                    maxLines = 1
                )
            },
            value = value,
            innerTextField = innerTextField,
            singleLine = singleLine,
            contentPadding = PaddingValues(0.dp),
        )
    }
}