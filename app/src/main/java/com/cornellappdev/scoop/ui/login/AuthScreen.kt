package com.cornellappdev.scoop.ui.login

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.repositories.LoginRepository
import com.cornellappdev.scoop.data.repositories.SignedInUser
import com.cornellappdev.scoop.ui.navigation.MainScreen
import com.cornellappdev.scoop.ui.theme.Green
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException


@Composable
fun AuthFlow(signInClient: GoogleSignInClient) {

    val navController = rememberNavController()

    // Set up the NavHost with the navigation graph
    NavHost(navController = navController, startDestination = "AUTH") {
        composable("AUTH") {
            AuthScreen(signInClient, navController)
        }
        composable("HOME") {
            MainScreen()

        }
    }

}


@Composable
fun AuthScreen(signInClient: GoogleSignInClient, navController: NavHostController) {

    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (result.data != null) {
                    val task: Task<GoogleSignInAccount> =
                        GoogleSignIn.getSignedInAccountFromIntent(intent)

                    task.addOnCompleteListener { res ->
                        if (res.isSuccessful) {
                            val account = res.result
                            // Successful sign in
                            val res =
                                SignedInUser(
                                    firstName = account.givenName!!,
                                    lastName = account.familyName!!, id = account.id!!,
                                    idToken = account.idToken!!, email = account.email!!
                                )

                            LoginRepository.retrieveAccessToken(res).enqueue(object : Callback {

                                override fun onFailure(call: Call, e: IOException) {
                                    Log.d("Signin", "Sign in error")
                                }

                                override fun onResponse(call: Call, response: Response) {

                                    // Parse response
                                    val responseJson = JSONObject(response.body!!.string())

                                    var accessToken = responseJson.getString("access_token")

                                    response.close()
                                    Log.d("Signin", "Successfully authenticated $accessToken")

                                    LoginRepository.headers["Authorization"] = "Token $accessToken"

                                    CoroutineScope(Dispatchers.Main).launch {
                                        navController.navigate("HOME")
                                    }

                                }
                            })

                            Log.d("Signin", "Signed in successfully")
                        } else {
                            // Handle sign-in failure, e.g., show an error message or retry sign-in
                            val exception = res.exception
                            if (exception != null) {
                                exception.message?.let { Log.d("Signin", it) }
                            }
                        }
                    }

                }
            } else {
                Log.d("Signin", "Error. Result code: " + result.resultCode.toString())
            }
        }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = painterResource(R.drawable.scooped_logo),
            contentDescription = "",
            modifier = Modifier.padding(top = 100.dp, bottom = 100.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp)
                .weight(weight = 1f, false),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    startForResult.launch(signInClient.signInIntent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Green,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Log in with NetID",
                    modifier = Modifier.padding(6.dp),
                    color = Color.White
                )
            }
        }
    }

}

