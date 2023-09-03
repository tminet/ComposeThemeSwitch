package tmidev.composethemeswitch.ui.screen.screen_settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import tmidev.composethemeswitch.R
import tmidev.composethemeswitch.util.AppIcons
import tmidev.composethemeswitch.util.isCompatibleWithDynamicColors

/**
 * Compose the Settings Screen.
 *
 * @param modifier the [Modifier] to apply on container of this screen.
 * @param windowInsets the [WindowInsets] to apply on container of this screen.
 * @param onNavigateBack callback to navigate back from this screen.
 * @param viewModel the [SettingsViewModel]. Default is provided by [hiltViewModel].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets,
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    BackHandler(onBack = onNavigateBack)

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.settings)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            painter = painterResource(id = AppIcons.NavigateBack),
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
                .padding(paddingValues = innerPadding)
        ) {
            if (isCompatibleWithDynamicColors()) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.useDynamicColors),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Switch(
                        checked = screenState.useDynamicColors,
                        onCheckedChange = { viewModel.toggleDynamicColors() }
                    )
                }

                Spacer(modifier = Modifier.height(height = 8.dp))
            }

            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = stringResource(id = R.string.themeStyle),
                style = MaterialTheme.typography.bodyLarge
            )

            ThemeStyleSection(
                modifier = Modifier.padding(horizontal = 32.dp),
                themeStyle = screenState.themeStyle,
                changeThemeStyle = { viewModel.changeThemeStyle(themeStyle = it) }
            )
        }
    }
}