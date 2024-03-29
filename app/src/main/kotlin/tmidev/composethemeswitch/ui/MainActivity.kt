package tmidev.composethemeswitch.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import tmidev.composethemeswitch.R
import tmidev.composethemeswitch.ui.component.AppLoadingAnimation
import tmidev.composethemeswitch.ui.component.appWindowInsets
import tmidev.composethemeswitch.ui.component.theme.AppTheme
import tmidev.composethemeswitch.ui.navigation.TopNavHost
import tmidev.composethemeswitch.util.shouldUseDarkTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val activityState by viewModel.activityState.collectAsStateWithLifecycle()
            val windowsInsets = appWindowInsets()

            when (activityState.isLoading) {
                true -> AppTheme {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .windowInsetsPadding(insets = windowsInsets),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AppLoadingAnimation()

                        Spacer(modifier = Modifier.height(height = 16.dp))

                        Text(
                            text = stringResource(id = R.string.appName),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.displayMedium
                        )
                    }
                }

                false -> AppTheme(
                    useDarkTheme = shouldUseDarkTheme(themeStyle = activityState.themeStyle),
                    useDynamicColors = activityState.useDynamicColors
                ) {
                    TopNavHost(
                        modifier = Modifier.fillMaxSize(),
                        windowInsets = windowsInsets,
                        onNavigateBack = { moveTaskToBack(true) }
                    )
                }
            }
        }
    }
}