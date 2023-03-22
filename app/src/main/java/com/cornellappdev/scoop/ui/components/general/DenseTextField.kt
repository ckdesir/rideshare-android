package com.cornellappdev.scoop.ui.components.general

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.ui.theme.Green
import com.cornellappdev.scoop.ui.theme.PlaceholderGray

/**
 * Creates a dense TextField, e.g. a TextField with no internal padding.
 *
 * @param value The text to be displayed
 * @param setValue The callback that is triggered when the input service updates the text.
 *      An updated text comes as a parameter of the callback.
 * @param placeholderText The placeholder to be displayed when the text field is in focus and the input text is empty
 * @param modifier Modifier to be applied to the [DenseTextField]
 * @param singleLine When set to true, this text field becomes
 *      a single horizontally scrolling text field instead of wrapping onto multiple lines.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DenseTextField(
    value: String,
    setValue: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    label: String,
    phoneNumber: Boolean = false,
    singleLine: Boolean = false,
    textStyle: TextStyle = TextStyle(
        color = Color.Black,
        fontSize = 22.sp,
        textAlign = TextAlign.Start
    )
) {
    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Green,
            cursorColor = Green,
            focusedLabelColor = Green
        ),
        value = value,
        onValueChange = setValue,
        placeholder = {
            Text(
                text = placeholderText,
                color = PlaceholderGray
            )
        },
        modifier = modifier
            .height(65.dp)
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.Black, fontSize = 17.sp),
        label = { Text(text = label) },
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(keyboardType = if (phoneNumber) KeyboardType.Phone else KeyboardType.Text)
    )
}