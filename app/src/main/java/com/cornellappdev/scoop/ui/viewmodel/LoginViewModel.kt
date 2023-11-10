package com.cornellappdev.scoop.ui.viewmodel

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.ui.components.general.MovingCarFooter
import com.cornellappdev.scoop.ui.theme.Green
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.annotation.Signed

data class SignedInUser(val firstName : String, val lastName : String, val id : String, val idToken : String, val email : String)

class LoginViewModel(val client: GoogleSignInClient) {

    private val _userFlow: MutableStateFlow<ApiResponse<SignedInUser>> =
        MutableStateFlow(ApiResponse.Pending)

    val userFlow = _userFlow.asStateFlow()


    fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        task.addOnCompleteListener { result ->
            if (result.isSuccessful) {
                val account = result.result
                // Successful sign in
                _userFlow.value = ApiResponse.Success(SignedInUser(firstName = account.givenName!!,
                    lastName = account.familyName!!, id = account.id!!,
                    idToken = account.idToken!!, email = account.email!!))

                Log.d("Signin", "Signed in successfully")
            } else {
                // Handle sign-in failure, e.g., show an error message or retry sign-in
                val exception = result.exception
                if (exception != null) {
                    exception.message?.let { Log.d("Signin", it) }
                }
                _userFlow.value = ApiResponse.Error
            }
        }
    }
}