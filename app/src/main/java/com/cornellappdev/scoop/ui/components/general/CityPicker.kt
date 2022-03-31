package com.cornellappdev.scoop.ui.components.general

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.ui.theme.PlaceholderGray
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CityPicker(
    cityState: MutableState<String>,
    modifier: Modifier = Modifier,
    placeholder: String,
    onCityChanged: (String) -> Unit
) {
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
                    cityState.value = place.address!!
                    onCityChanged(place.address!!)
                }
            }
        }

    TextButton(
        modifier = modifier,
        contentPadding = PaddingValues(0.dp),
        onClick = { launcher.launch(intent) }) {
        Column {
            if (cityState.value.isEmpty()) {
                Text(
                    text = placeholder,
                    style = TextStyle(color = PlaceholderGray, fontSize = 22.sp),
                )
            } else {
                Text(
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    text = cityState.value,
                    style = TextStyle(color = Color.Black, fontSize = 22.sp),
                )
            }

            Divider(color = Color.Black, thickness = 2.dp)
        }
    }
}