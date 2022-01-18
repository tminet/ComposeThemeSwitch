package tmidev.themeswitch.presentation.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tmidev.themeswitch.domain.type.ScreenRouteType
import tmidev.themeswitch.presentation.MainViewModel
import tmidev.themeswitch.presentation.screen_home.HomeScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRouteType.HomeScreen.route
    ) {
        composable(
            route = ScreenRouteType.HomeScreen.route
        ) {
            HomeScreen(mainViewModel = mainViewModel)
        }
    }
}