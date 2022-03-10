package com.cornellappdev.scoop

/**
 * Main screen metadata for Scoop.
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
                null, in Routes.NON_TAB_ROUTES -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}

/**
 * Route metadata for Scoop/Navigation system.
 */
enum class Routes(
    val route: String
) {
    Home(ScoopScreen.Home.name),
    Search(ScoopScreen.Search.name),
    Profile(ScoopScreen.Profile.name),
    Post("POST"),
    View("VIEW"),
    SignIn("SIGNIN");

    companion object {
        val TAB_ROUTES = listOf(Home.route, Search.route, Profile.route)
        val NON_TAB_ROUTES = listOf(Post.route, View.route, SignIn.route)
    }
}