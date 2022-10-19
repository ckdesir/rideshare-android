package com.cornellappdev.scoop.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cornellappdev.scoop.data.models.User

class UserScreenViewModel() : ViewModel() {

    data class UserScreenUiState(
        val user: User = User(),
        val message: String = ""
    )

    var userUiState = mutableStateOf(UserScreenUiState())
        private set


}