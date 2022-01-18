package tmidev.themeswitch.presentation.screen_home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import tmidev.themeswitch.R
import tmidev.themeswitch.presentation.MainViewModel
import tmidev.themeswitch.presentation.common.TopBarWithThemeSwitch

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val isAppThemeDarkMode = mainViewModel.isAppThemeDarkMode.value ?: isSystemInDarkTheme()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBarWithThemeSwitch(
            title = R.string.titleHomeScreen,
            darkMode = isAppThemeDarkMode
        ) {
            mainViewModel.updateAppTheme(darkMode = !isAppThemeDarkMode)
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = stringResource(id = R.string.appName))
        }
    }
}
