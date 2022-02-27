package com.cornellappdev.scoop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.cornellappdev.scoop.components.ScoopTabRow
import com.cornellappdev.scoop.screens.HomeBody
import com.cornellappdev.scoop.screens.ProfileBody
import com.cornellappdev.scoop.screens.SearchBody
import com.cornellappdev.scoop.ui.theme.ScoopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScoopApp()
        }
    }
}

@Composable
fun ScoopApp() {
    ScoopTheme {
        val allScreens = ScoopScreen.values().toList()
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = ScoopScreen.fromRoute(
            backstackEntry.value?.destination?.route
        )
        val (showBottomBar, setShowBottomBar) = rememberSaveable { mutableStateOf(true) }
        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    ScoopTabRow(
                        allScreens = allScreens,
                        onTabSelected = { screen -> navController.navigate(screen.name) },
                        currentScreen = currentScreen
                    )
                }
            }
        ) { innerPadding ->
            RallyNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                setShowBottomBar
            )
        }
    }
}

@Composable
fun RallyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    hideBottomBar: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = ScoopScreen.Home.name,
        modifier = modifier
    ) {
        composable(Routes.Home.route) {
            HomeBody(
                onTripClick = { identifier ->
                    navigateToTrip(navController, identifier)
                },
                onPostNewRide = {
                    // Could require information about user
                    navigateToPostFlow(navController)
                }
            )
        }
        composable(Routes.Search.route) {
            SearchBody()
        }
        composable(Routes.Profile.route) {
            ProfileBody()
        }
        val viewTrip = Routes.View.route
        composable(
            "$viewTrip/{trip_identifier}",
            arguments = listOf(
                navArgument("trip_identifier") {
                    type = NavType.StringType // TBD
                },
            ),
            // Adds deep linking in case we want to add sharing and pulling up a ride
            deepLinks = listOf(navDeepLink {
                uriPattern = "rally/$viewTrip/{trip_identifier}"
            }),
        ) { entry ->
            val tripIdentifier = entry.arguments?.getString("trip_identifier")
            // Retrieve trip information
            // Build composable for displaying trip
        }
        val postNewTrip = Routes.Post.route
        composable(Routes.Post.route) {
            // Initiate posting new trip body
        }
    }
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

