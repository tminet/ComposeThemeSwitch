package tmidev.composethemeswitch.domain.type

sealed class ScreenRouteType(val route: String) {
    data object Home : ScreenRouteType(route = "home_screen")
    data object Settings : ScreenRouteType(route = "settings_screen")
}