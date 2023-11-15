package com.cornellappdev.scoop.data.repositories

import android.content.Context
import android.provider.Settings.Global.getString
import android.util.Log
import com.cornellappdev.scoop.BuildConfig
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.NetworkApi
import com.cornellappdev.scoop.ui.viewmodel.ApiResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.squareup.moshi.Json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * The five parameters needed from Google Sign In to retrieve an access token
 */
data class SignedInUser(
    @Json(name = "given_name") val firstName: String,
    @Json(name = "family_name") val lastName: String,
    @Json(name = "sub") val id: String,
    @Json(name = "id_token") val idToken: String,
    @Json(name = "email") val email: String
)

/**
 * The response from the authentication flow
 */
data class AuthenticateResponse(
    @Json(name = "access_token") val accessToken: String,
    @Json(name = "username") val username: String,
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String
)


object LoginRepository {

    var headers: MutableMap<String, String> = mutableMapOf()

    /**
     * Get access token from backend
     */
    fun retrieveAccessToken(signedInUser: SignedInUser): Call {
        val client = OkHttpClient()

        // Define the JSON payload
        val jsonBody = """
                    {
                    "sub": "${signedInUser.id}",
                    "email": "${signedInUser.email}",
                    "given_name": "${signedInUser.firstName}",
                    "family_name": "${signedInUser.lastName}",
                    "id_token": "${signedInUser.idToken}"
                    }
                    """.trimIndent()
        val requestBody = jsonBody.toRequestBody("application/json".toMediaTypeOrNull())

        // Create the request
        val request = Request.Builder()
            .url(BuildConfig.BACKEND_URL.plus("api/authenticate/"))
            .post(requestBody)
            .build()

        return client.newCall(request)


    }

}