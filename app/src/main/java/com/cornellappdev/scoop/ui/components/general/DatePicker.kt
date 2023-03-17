package com.cornellappdev.scoop.ui.components.general

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.ui.components.post.createDatePickerDialog
import com.cornellappdev.scoop.ui.theme.PlaceholderGray
import java.text.SimpleDateFormat
import java.util.*

/**
 * Allows users to select a city using Place API autocomplete.
 *
 * @param dateState State that represents the current time selected by user
 * @param placeholder The placeholder to be displayed when the text field is in focus and the input text is empty
 * @param modifier Modifier to be applied to the [DatePicker]
 * @param placeholderColor [Color] to apply to the placeholder text
 * @param enabled Controls the enabled state of the button. When false, this button will not be clickable.
 * @param disabledTextStyle The optional text style to be applied to the text if the [DatePicker] is not enabled
 * @param disableDivider Controls the divider below the text. When false, the divider will not be there.
 * @param onDateChanged Callback called when the user changes the time selected.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DatePicker(
    dateState: MutableState<String>,
    placeholder: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    placeholderColor: Color = PlaceholderGray,
    enabled: Boolean = true,
    disabledTextStyle: TextStyle = TextStyle(color = Color.Black, fontSize = 22.sp),
    disableDivider: Boolean = false,
    onDateChanged: (String) -> Unit = { it -> dateState.value = it },
) {

    val dateFormatter =
        SimpleDateFormat(stringResource(R.string.date_format), Locale.getDefault())

    val datePickerDialog = createDatePickerDialog(
        LocalContext.current,
        { Log.d("Date changed", it); onDateChanged(it) },
        dateFormatter
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                1.dp,
                Color.Gray,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                datePickerDialog.show()
            }
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            Icon(
                icon,
                null,
                modifier = Modifier
                    .padding(vertical = 15.dp)
            )
            Spacer(
                modifier = Modifier.width(5.dp)
            )
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                if (dateState.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.body1.copy(color = placeholderColor)
                    )
                } else {
                    Text(
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        text = dateState.value,
                        style = MaterialTheme.typography.body1
                    )
                }
                if (!disableDivider) {
                    Divider(color = Color.Black, thickness = 2.dp)
                }
            }
        }
    }
}