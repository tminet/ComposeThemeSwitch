package tmidev.themeswitch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tmidev.themeswitch.presentation.common.MainNavHost
import tmidev.themeswitch.presentation.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel = hiltViewModel<MainViewModel>()
            val navController = rememberNavController()
            AppTheme(
                darkTheme = mainViewModel.isAppThemeDarkMode.value ?: isSystemInDarkTheme()
            ) {
                Surface(color = MaterialTheme.colors.background) {
                    MainNavHost(
                        navController = navController,
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}