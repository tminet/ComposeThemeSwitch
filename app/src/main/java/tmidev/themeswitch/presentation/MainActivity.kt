package tmidev.themeswitch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tmidev.themeswitch.domain.type.ScreenRouteType
import tmidev.themeswitch.presentation.common.theme.AppTheme
import tmidev.themeswitch.presentation.common.theme.SplashTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            val navController = rememberNavController()

            val state = viewModel.state

            val isAppThemeDarkMode = state.isAppThemeDarkMode ?: isSystemInDarkTheme()
            val startDestination = ScreenRouteType.Home

            if (state.isLoading) ComposeWaitState()
            else ComposeContentState(
                navController = navController,
                startDestination = startDestination,
                mainViewModel = viewModel,
                isAppThemeDarkMode = isAppThemeDarkMode
            )
        }
    }

    @Composable
    private fun ComposeWaitState() = SplashTheme()

    @Composable
    private fun ComposeContentState(
        navController: NavHostController,
        startDestination: ScreenRouteType,
        mainViewModel: MainViewModel,
        isAppThemeDarkMode: Boolean
    ) = AppTheme(darkTheme = isAppThemeDarkMode) {
        MainNavHost(
            navController = navController,
            startDestination = startDestination,
            mainViewModel = mainViewModel
        )
    }
}