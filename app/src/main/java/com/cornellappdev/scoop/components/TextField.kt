package com.cornellappdev.scoop.components

import android.view.KeyEvent
import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.ui.theme.Typography
import androidx.compose.ui.text.TextStyle as AndroidTextStyle

@Composable
fun TextField(
    value: String,
    onValueChange: (updated: String) -> Unit,
    placeholder: String,
    onSubmit: () -> Unit = {},
    backgroundColor: Color = Color.White,
    focusRequester: FocusRequester? = null,
    isPassword: Boolean = false,
    leftIcon: Painter? = null
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .background(backgroundColor)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leftIcon != null) {
                Icon(
                    painter = leftIcon,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                maxLines = 1,
                singleLine = true,
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
                decorationBox = { innerTextField ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                textStyle = AndroidTextStyle,
                                color = Color.Gray,
                            )
                        }
                    }
                    innerTextField()
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onSubmit()
                    }
                ),
                modifier = Modifier
                    .onKeyEvent {
                        if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                            onSubmit()
                            true
                        } else {
                            false
                        }
                    }
                    .then(
                        if (focusRequester != null)
                            Modifier.focusRequester(focusRequester)
                        else Modifier
                    )
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
            )
        }
    }
}