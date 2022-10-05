package com.cornellappdev.scoop.ui.components.search

import android.annotation.SuppressLint
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.data.models.Search
import com.cornellappdev.scoop.ui.components.general.FilterRow
import com.cornellappdev.scoop.ui.components.general.RideCard

/**
 * This page allows users to look at their search, filter and view their results.
 *
 * Users also have the ability to edit their search.
 *
 * @param searchState State that represents the current search the user inputted
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DisplaySearchesPage(searchState: MutableState<Search>) {
    val searchResults: MutableState<List<Ride>> = remember {
        mutableStateOf(listOf())
    }
    val filter = rememberSaveable { mutableStateOf<String?>(null) }
    val isEditing = rememberSaveable { mutableStateOf(false) }

    Scaffold(topBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp)
                .height(30.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isEditing.value) {
                    IconButton(onClick = {
                        isEditing.value = false
                    }) {
                        Icon(Icons.Filled.Close, stringResource(R.string.stop_editing))
                    }
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isEditing.value) stringResource(R.string.edit_your_search) else stringResource(
                        R.string.best_matches
                    ),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(fontSize = 24.sp)
                )
            }
        }
    }) {
        Column(modifier = Modifier.padding(10.dp)) {
            SearchCard(
                searchState,
                filter,
                isEditing
            ) {
                // To save computation time, try adding/replacing only what's new, etc.
                searchResults.value = it
            }

            FilterRow(
                filter,
                stringArrayResource(id = R.array.methods_of_transportation).asList(),
                modifier = Modifier
                    .padding(vertical = 25.dp)
            ) {
                // Requery and apply filter if applicable.
                // To save computation time, try adding/replacing only what's new, etc.
            }

            Box(Modifier.weight(1f)) {

                val state = rememberLazyListState()

                LazyColumn(
                    state = state,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(searchResults.value) { item ->
                        RideCard(item)
                    }
                }

                // Gradient overlay to the bottom of the Search results LazyColumn
                androidx.compose.animation.AnimatedVisibility(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .height(100.dp)
                        .fillMaxWidth(),
                    enter = fadeIn(),
                    exit = fadeOut(),
                    // The gradient overlay is only visible when the user hasn't scrolled to the end
                    // so the gradient isn't blocking the final card
                    visible = !state.isScrolledToTheEnd()
                ) {
                    Spacer(
                        Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.White
                                    )
                                )
                            )
                    )
                }
            }
        }
    }
}

/**
 * Detects when the given LazyList has scrolled to the end.
 */
fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1