package com.cornellappdev.scoop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
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
import com.cornellappdev.rideshare.GoogleSignInView
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
                AnimatedVisibility(visible = showBottomBar) {
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

/**
 * Maps the NavHost routes to various screens in the app.
 */
@Composable
fun RallyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    setShowBottomBar: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SignIn.route,
        modifier = modifier
    ) {
        composable(Routes.SignIn.route) {
            setShowBottomBar(false)
            GoogleSignInView(navController = navController)
        }
        composable(Routes.Home.route) {
            CreateHomeBody(navController)
        }
        composable(
            "${Routes.Home.route}/{show_trip_posted}",
            arguments = listOf(
                navArgument("show_trip_posted") {
                    type = NavType.BoolType; defaultValue = false
                },
            )
        ) { entry ->
            setShowBottomBar(true)
            val showTripPosted = entry.arguments?.getBoolean("show_trip_posted")
            CreateHomeBody(navController, showTripPosted!!)
        }
        composable(Routes.Search.route) {
            setShowBottomBar(true)
            SearchBody()
        }
        composable(Routes.Profile.route) {
            setShowBottomBar(true)
            ProfileBody()
        }
        val viewTrip = Routes.View.route
        composable(
            "$viewTrip/{trip_identifier}",
            arguments = listOf(
                navArgument("trip_identifier") {
                    type = NavType.StringType
                },
            ),
            // Adds deep linking in case we want to add sharing and pulling up a ride automatically.
            deepLinks = listOf(navDeepLink {
                uriPattern = "rally/$viewTrip/{trip_identifier}"
            }),
        ) { entry ->
            // Placeholder for eventual retrieving trip logic
            // val tripIdentifier = entry.arguments?.getString("trip_identifier")
            // Retrieve trip information using whatever identifier
            // Build composable for displaying trip
        }
        composable(Routes.Post.route) {
            setShowBottomBar(false)
//            PostBody(
//                onPostTrip = { trip ->
//                    // Post to backend
//                    // Return home - navController.navigate("{Routes.Home.route}/${result}")
//                }
//            )

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

@Composable
private fun CreateHomeBody(navController: NavHostController, showTripPosted: Boolean = false) {
    HomeBody(
        onTripClick = { identifier ->
            navigateToTrip(navController, identifier)
        },
        onPostNewRide = {
            navigateToPostFlow(navController)
        },
        showTripPosted
    )
}
