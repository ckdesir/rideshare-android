package com.cornellappdev.scoop.ui.components.general

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.BuildConfig
import com.cornellappdev.scoop.ui.theme.PlaceholderGray
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import java.util.*

/**
 * Allows users to select a city using Place API autocomplete.
 *
 * @param cityState State that represents the current city selected by user
 * @param placeholder The placeholder to be displayed when the text field is in focus and the input text is empty
 * @param modifier Modifier to be applied to the [CityPicker]
 * @param placeholderColor [Color] to apply to the placeholder text
 * @param enabled Controls the enabled state of the button. When false, this button will not be clickable.
 * @param disabledTextStyle The optional text style to be applied to the text if the [CityPicker] is not enabled
 * @param disableDivider Controls the divider below the text. When false, the divider will not be there.
 * @param onCityChanged Callback called when the user changes the city selected. If the user selects the same
 *      city as previously selected, this function will not be called.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CityPicker(
    cityState: MutableState<String>,
    placeholder: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    placeholderColor: Color = PlaceholderGray,
    enabled: Boolean = true,
    disabledTextStyle: TextStyle = TextStyle(color = Color.Black, fontSize = 22.sp),
    disableDivider: Boolean = false,
    onCityChanged: (String) -> Unit = {}
) {
    if (!Places.isInitialized()) {
        Places.initialize(LocalContext.current, BuildConfig.PLACES_API_KEY, Locale.US)
    }

    // Set the fields to specify which types of place data to
    // return after the user has made a selection.
    val fields = listOf(Place.Field.ID, Place.Field.ADDRESS)

    // Start the autocomplete intent.
    val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
        .setTypeFilter(TypeFilter.CITIES)
        .build(LocalContext.current)
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val place = Autocomplete.getPlaceFromIntent(result.data!!)
                    if (place.address!! != cityState.value) {
                        cityState.value = place.address!!
                        onCityChanged(place.address!!)
                    }
                }
            }
        }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                1.dp,
                Color.Gray,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { launcher.launch(intent) }
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
                if (cityState.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.body1.copy(color = placeholderColor)
                    )
                } else {
                    Text(
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        text = cityState.value,
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