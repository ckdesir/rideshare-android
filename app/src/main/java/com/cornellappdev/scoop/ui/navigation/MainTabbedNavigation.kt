package com.cornellappdev.scoop.ui.navigation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.cornellappdev.scoop.ui.screens.HomeScreen
import com.cornellappdev.scoop.ui.screens.PostScreen
import com.cornellappdev.scoop.ui.screens.ProfileScreen
import com.cornellappdev.scoop.ui.screens.SearchScreen
import com.google.accompanist.pager.ExperimentalPagerApi

/** This composable makes the bottom nav bar and base layer on which different screens are shown. */
@Composable
fun MainScreen(
) {
    val navController = rememberNavController()
    navController.currentBackStackEntryAsState()
    val bottomNavTabList = BottomNavTab.bottomNavTabList
    val (showBottomBar, setShowBottomBar) = rememberSaveable { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(visible = showBottomBar) {
                BottomNav(navController = navController, tabItems = bottomNavTabList)
            }
        }
    ) { innerPadding ->
        MainScreenNavigationConfigurations(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            setShowBottomBar = setShowBottomBar
        )
    }
}

/**
 * This defines the behavior and look of each tab.
 */
@Composable
fun BottomNav(navController: NavHostController, tabItems: List<BottomNavTab>) {
    BottomNavigation(backgroundColor = Color.White) {
        val currentRoute = currentRoute(navController)
        tabItems.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = if (currentRoute == screen.route) screen.selectedIconId else screen.unselectedIconId),
                        contentDescription = screen.contentDescription
                    )
                },
                selected = currentRoute == screen.route,
                selectedContentColor = Color.Unspecified,
                unselectedContentColor = Color.Unspecified,
                modifier = Modifier.padding(10.dp),
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

/**
 * Maps the NavHost routes to various screens in the app.
 */
@OptIn(
    ExperimentalAnimationApi::class, ExperimentalPagerApi::class
)
@Composable
fun MainScreenNavigationConfigurations(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    setShowBottomBar: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        modifier = modifier
    ) {
        composable(Routes.Home.route) {
            setShowBottomBar(true)
            HomeScreen(
                onTripClick = { identifier ->
                    navigateToTrip(navController, identifier)
                },
                onPostNewRide = {
                    navigateToPostFlow(navController)
                }
            )
        }
        composable(
            Routes.HomeShowTripPosted.route,
            arguments = listOf(
                navArgument("show_trip_posted") {
                    type = NavType.BoolType; defaultValue = false
                },
            )
        ) { entry ->
            setShowBottomBar(true)
            val showTripPosted = entry.arguments?.getBoolean("show_trip_posted")
            HomeScreen(
                onTripClick = { identifier ->
                    navigateToTrip(navController, identifier)
                },
                onPostNewRide = {
                    navigateToPostFlow(navController)
                },
                showTripPosted = showTripPosted!!
            )
        }
        composable(Routes.Search.route) {
            setShowBottomBar(true)
            SearchScreen()
        }
        composable(Routes.Profile.route) {
            setShowBottomBar(true)
            ProfileScreen()
        }
        composable(
            Routes.View.route,
            arguments = listOf(
                navArgument("trip_identifier") {
                    type = NavType.StringType
                },
            ),
            // Adds deep linking in case we want to add sharing and pulling up a ride automatically.
            deepLinks = listOf(navDeepLink {
                uriPattern = "rally/${Routes.View.route}/{trip_identifier}"
            }),
        ) { entry ->
            // Placeholder for eventual retrieving trip logic
            // val tripIdentifier = entry.arguments?.getString("trip_identifier")
            // Retrieve trip information using whatever identifier
            // Build composable for displaying trip
        }
        composable(Routes.Post.route) {
            setShowBottomBar(false)
            PostScreen(
                onPostNewTrip = {
                    Log.d("Trip", it.arrivalLocation!!)
                })
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

private fun navigateToTrip(
    navController: NavHostController,
    trip_identifier: String
) {
    navController.navigate("${Routes.View.route}/$trip_identifier")
}

private fun navigateToPostFlow(
    navController: NavHostController,
) {
    navController.navigate(Routes.Post.route)
}

private const val InactiveTabOpacity = 0.60f