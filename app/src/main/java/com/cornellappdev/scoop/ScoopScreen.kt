package com.cornellappdev.scoop

import android.media.Image
import android.provider.ContactsContract
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image

/**
 * Screen metadata for Scoop.
 */
enum class ScoopScreen(
    val iconId: Int
) {
    Home(
        iconId = R.drawable.ic_home_icon
    ),
    Search(
        iconId = R.drawable.ic_search_icon,
    ),
    Profile(
        iconId = R.drawable.ic_profile_icon,
    );

    companion object {
        fun fromRoute(route: String?): ScoopScreen =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                Search.name -> Search
                Profile.name -> Profile
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}

enum class Routes(
    val route: String
) {
    Home(ScoopScreen.Home.name),
    Search(ScoopScreen.Search.name),
    Profile(ScoopScreen.Profile.name),
    Post("POST"),
    View("VIEW")
}