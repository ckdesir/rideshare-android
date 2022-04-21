package com.cornellappdev.scoop.ui.navigation

import com.cornellappdev.scoop.R

/**
 * All NavUnit must have a route (which specifies where to
 * navigate to).
 */
interface NavUnit {
    val route: String
}

/**
 * Data class to represent each tab.
 *
 * The route is needed to match the tab to the correct screen.
 * The iconId is the resource id number for the drawable for the icon of the tab.
 */
data class BottomNavTab(
    override var route: String,
    val iconId: Int,
    val contentDescription: String
) :
    NavUnit {
    companion object {
        val bottomNavTabList = listOf(
            BottomNavTab("HOME", R.drawable.ic_home_icon, "Navigate to home"),
            BottomNavTab("SEARCH", R.drawable.ic_search_icon, "Search up a trip"),
            BottomNavTab("PROFILE", R.drawable.ic_profile_icon, "View my profile")
        )
    }
}

/**
 * Contains information about all known routes. These should correspond to routes in our
 * NavHost/new routes should be added here.
 */
enum class Routes(override var route: String) : NavUnit {
    Home("HOME"),
    HomeShowTripPosted("HOME/{show_trip_posted}"),
    Search("SEARCH"),
    Profile("PROFILE"),
    Post("POST"),
    View("VIEW/{trip_identifier}");
}