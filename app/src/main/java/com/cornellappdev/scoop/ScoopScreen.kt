package com.cornellappdev.scoop

/**
 * Screen metadata for Scoop.
 */
enum class ScoopScreen(
    val icon: Int
) {
    Home(
        icon = R.drawable.ic_home_icon
    ),
    Search(
        icon = R.drawable.ic_search_icon,
    ),
    Profile(
        icon = R.drawable.ic_profile_icon,
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