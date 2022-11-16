package com.cornellappdev.scoop.ui.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Creates a dense TextField, e.g. a TextField with no internal padding.
 *
 * @param value The text to be displayed
 * @param setValue The callback that is triggered when the input service updates the text.
 *      An updated text comes as a parameter of the callback.
 * @param placeholderText The placeholder to be displayed when the text field is in focus and the input text is empty
 * @param modifier Modifier to be applied to the [DenseTextField]
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
fun DenseTextField(
    value: String,
    setValue: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    label: String,
    phoneNumber: Boolean = false,
    textStyle: TextStyle = TextStyle(
        color = Color.Black,
        fontSize = 22.sp,
        textAlign = TextAlign.Start
    )
) {
    OutlinedTextField(
        value = value,
        onValueChange =setValue,
        placeholder = {Text(text=placeholderText)},
        modifier = modifier
            .height(65.dp)
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.Black, fontSize = 17.sp),
        label={Text(text=label)},
        keyboardOptions = KeyboardOptions(keyboardType = if (phoneNumber) KeyboardType.Phone else KeyboardType.Text)
    )
}