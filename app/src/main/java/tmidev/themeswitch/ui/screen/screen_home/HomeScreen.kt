package tmidev.themeswitch.ui.screen.screen_home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tmidev.themeswitch.R
import tmidev.themeswitch.util.AppIcons

/**
 * Compose the Home Screen.
 *
 * @param modifier the [Modifier] to apply on container of this screen.
 * @param windowInsets the [WindowInsets] to apply on container of this screen.
 * @param onNavigateBack callback to navigate back from this screen.
 * @param navigateToSettingsScreen callback to navigate to settings screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets,
    onNavigateBack: () -> Unit,
    navigateToSettingsScreen: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(state = rememberTopAppBarState())

    BackHandler(onBack = onNavigateBack)

    Scaffold(
        modifier = modifier.nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.home)) },
                actions = {
                    IconButton(onClick = navigateToSettingsScreen) {
                        Icon(
                            painter = painterResource(id = AppIcons.Settings),
                            contentDescription = stringResource(id = R.string.settings)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        contentWindowInsets = windowInsets
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding),
            contentPadding = PaddingValues(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            items(count = 50) { index ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(all = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
                    ) {
                        Text(text = stringResource(id = R.string.dummyItemIndex, index))

                        Text(
                            text = stringResource(id = R.string.changeAppThemeInstruction),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}