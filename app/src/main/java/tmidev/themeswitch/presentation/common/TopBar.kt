package tmidev.themeswitch.presentation.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import tmidev.themeswitch.R
import tmidev.themeswitch.presentation.theme.elevating
import tmidev.themeswitch.presentation.theme.spacing

@Composable
private fun TopBar(
    content: @Composable RowScope.() -> Unit
) = TopAppBar(
    backgroundColor = MaterialTheme.colors.primary,
    elevation = MaterialTheme.elevating.none,
    contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.extraSmall),
    modifier = Modifier.fillMaxWidth(),
    content = content
)

@Composable
fun TopBarWithThemeSwitch(
    @StringRes title: Int,
    darkMode: Boolean,
    onThemeSwitchClick: () -> Unit
) {
    val themeSwitchIcon = if (darkMode) Icons.Outlined.LightMode else Icons.Outlined.DarkMode
    TopBar {
        Text(
            text = stringResource(id = title),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .weight(weight = 1F)
        )
        IconButton(
            onClick = { onThemeSwitchClick() }
        ) {
            Icon(
                imageVector = themeSwitchIcon,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = stringResource(id = R.string.switchAppTheme)
            )
        }
    }
}