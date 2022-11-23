package tmidev.themeswitch.ui.screen.screen_settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import tmidev.themeswitch.R

/**
 * Compose the Settings Screen.
 *
 * @param modifier modifier to apply on the container of this screen.
 * @param windowInsets [WindowInsets] to be used on this screen.
 * @param onBack [BackHandler] and/or other triggers to navigate back, such as Navigation Icon from
 * TopAppBar.
 * @param viewModel [SettingsViewModel] for this screen. Default is provided by [hiltViewModel].
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets,
    onBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    BackHandler(onBack = onBack)

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.settings)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_navigate_back),
                            contentDescription = stringResource(id = R.string.navigateBack)
                        )
                    }
                }
            )
        },
        contentWindowInsets = windowInsets
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputChip(
                selected = screenState.isAppThemeDarkMode == null,
                onClick = {
                    if (screenState.isAppThemeDarkMode != null)
                        viewModel.updateAppTheme(darkMode = null)
                },
                label = { Text(text = stringResource(id = R.string.followAndroidSystem)) },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(size = AssistChipDefaults.IconSize),
                        painter = painterResource(id = R.drawable.ic_android),
                        contentDescription = null
                    )
                }
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                InputChip(
                    selected = screenState.isAppThemeDarkMode == false,
                    onClick = {
                        if (screenState.isAppThemeDarkMode != false)
                            viewModel.updateAppTheme(darkMode = false)
                    },
                    label = { Text(text = stringResource(id = R.string.lightMode)) },
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.size(size = AssistChipDefaults.IconSize),
                            painter = painterResource(id = R.drawable.ic_light_mode),
                            contentDescription = null
                        )
                    }
                )

                InputChip(
                    selected = screenState.isAppThemeDarkMode == true,
                    onClick = {
                        if (screenState.isAppThemeDarkMode != true)
                            viewModel.updateAppTheme(darkMode = true)
                    },
                    label = { Text(text = stringResource(id = R.string.darkMode)) },
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.size(size = AssistChipDefaults.IconSize),
                            painter = painterResource(id = R.drawable.ic_dark_mode),
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}