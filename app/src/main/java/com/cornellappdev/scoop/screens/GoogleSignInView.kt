package com.cornellappdev.rideshare

import android.app.Application
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cornellappdev.rideshare.components.SignInButton
import com.cornellappdev.scoop.Routes
import com.cornellappdev.scoop.google.GoogleSignInAPI
import com.cornellappdev.scoop.google.GoogleUserModel
import com.cornellappdev.scoop.google.SignInGoogleViewModelFactory
import com.cornellappdev.scoop.google.SignInViewModel
import com.google.android.gms.common.api.ApiException
import com.squareup.moshi.Moshi

@Composable
fun GoogleSignInView(
    navController: NavController
){
    val context = LocalContext.current

    val signInViewModel: SignInViewModel = viewModel(
        factory = SignInGoogleViewModelFactory(context.applicationContext as Application)
    )

    val state = signInViewModel.googleUser.observeAsState()
    val user = state.value
    val isError = rememberSaveable {mutableStateOf(false)}
    val authResultLauncher =
        rememberLauncherForActivityResult(contract = GoogleSignInAPI()){
            task ->
                try{
                    val gsa = task?.getResult(ApiException::class.java)
                    if (gsa != null) {
                        signInViewModel.fetchSignInUser(gsa.email, gsa.displayName)
                    } else {
                        isError.value = true
                    }
                } catch (e: ApiException) {
                    Log.d("Error in AuthScreen%s", e.toString())
                }
        }

    GoogleSignInView(
        onClick = { authResultLauncher.launch(1) },
        isError = isError.value,
        signInViewModel
    )

    user?.let {
        signInViewModel.hideLoading()

        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(GoogleUserModel::class.java).lenient()
        val userJson = jsonAdapter.toJson(user)

        Log.d("Sign In User: ", userJson.toString())

        navController.navigate(Routes.Home.route)

    }

}

@Composable
private fun GoogleSignInView(
    onClick: () -> Unit,
    isError: Boolean = false,
    signInViewModel: SignInViewModel
){
    val state = signInViewModel.loading.observeAsState()
    val isLoading = state.value

    Scaffold{
        if(isLoading!! && !isError){
            // Loading Component
            Box(
                modifier = Modifier.fillMaxSize())
            {
                CircularProgressIndicator(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                )
            }
        }
        else{
            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Spacer(modifier = Modifier.weight(1F))
                SignInButton(
                    onclick = {
                        signInViewModel.showLoading()
                        onClick()
                    }
                )
                Spacer(modifier = Modifier.weight(1F))
                Text(
                    text = "Scoop Text Here",
                    textAlign = TextAlign.Center,
                )

                when {
                    isError -> {
                        isError.let {
                            Text(
                                //We need to create a style for texts
                                text = "Error during sign in",
                                style = MaterialTheme.typography.h6,
                                color = MaterialTheme.colors.error
                            )
                            signInViewModel.hideLoading()
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAuthView() {
    Surface {
        Temp()
    }
}

@Composable
private fun Temp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1F))
        Spacer(modifier = Modifier.weight(1F))
        SignInButton(onclick = {})
        Spacer(modifier = Modifier.weight(1F))
        Text(
            text = "Bottom Text",
            textAlign = TextAlign.Center,
        )
    }
}