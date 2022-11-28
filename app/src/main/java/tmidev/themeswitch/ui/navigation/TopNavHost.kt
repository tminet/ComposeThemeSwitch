package tmidev.themeswitch.ui.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tmidev.themeswitch.domain.type.ScreenRouteType
import tmidev.themeswitch.ui.screen.screen_home.HomeScreen
import tmidev.themeswitch.ui.screen.screen_settings.SettingsScreen

/**
 * [NavHost] for top level screens.
 *
 * @param modifier the [Modifier] to apply on container of this nav host.
 * @param windowInsets [WindowInsets] to be used on each screen of this nav host.
 * @param onNavigateBack callback to navigate back from this nav host.
 * @param navController the [NavHostController] for this nav host. Default is
 * [rememberNavController].
 * @param startDestination string route for the start screen of this nav host. Default is
 * [ScreenRouteType.Home.route].
 */
@Composable
fun TopNavHost(
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets,
    onNavigateBack: () -> Unit,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ScreenRouteType.Home.route
) = NavHost(
    modifier = modifier,
    navController = navController,
    route = "top_level_nav_host",
    startDestination = startDestination
) {
    composable(route = ScreenRouteType.Home.route) {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            windowInsets = windowInsets,
            onNavigateBack = onNavigateBack,
            navigateToSettingsScreen = {
                navController.navigate(route = ScreenRouteType.Settings.route) {
                    launchSingleTop = true
                }
            }
        )
    }

    composable(route = ScreenRouteType.Settings.route) {
        SettingsScreen(
            modifier = Modifier.fillMaxSize(),
            windowInsets = windowInsets,
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    }
}