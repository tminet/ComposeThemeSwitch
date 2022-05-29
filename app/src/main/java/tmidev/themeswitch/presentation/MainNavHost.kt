package tmidev.themeswitch.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tmidev.themeswitch.domain.type.ScreenRouteType
import tmidev.themeswitch.presentation.screen_home.HomeScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: ScreenRouteType,
    mainViewModel: MainViewModel
) = NavHost(
    navController = navController,
    startDestination = startDestination.route
) {
    homeScreen(mainViewModel = mainViewModel)
}

private fun NavGraphBuilder.homeScreen(
    mainViewModel: MainViewModel
) = composable(
    route = ScreenRouteType.Home.route
) {
    HomeScreen(mainViewModel = mainViewModel)
}