package tmidev.themeswitch.domain.type

sealed class ScreenRouteType(val route: String) {
    object Home : ScreenRouteType(route = "home_screen")
    object Settings : ScreenRouteType(route = "settings_screen")
}