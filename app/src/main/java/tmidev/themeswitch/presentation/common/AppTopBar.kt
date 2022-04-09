package tmidev.themeswitch.presentation.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import tmidev.themeswitch.R
import tmidev.themeswitch.presentation.common.theme.elevating
import tmidev.themeswitch.presentation.common.theme.spacing

@Composable
private fun AppTopBar(
    backgroundColor: Color,
    content: @Composable RowScope.() -> Unit
) = TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    backgroundColor = backgroundColor,
    elevation = MaterialTheme.elevating.none,
    contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.extraSmall),
    content = content
)

@Composable
fun AppTopBarSimple(
    @StringRes title: Int,
    titleAlign: TextAlign = TextAlign.Start,
    titleColor: Color = MaterialTheme.colors.onPrimary,
    backgroundColor: Color = MaterialTheme.colors.primary
) = AppTopBar(backgroundColor = backgroundColor) {
    Text(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .fillMaxWidth(),
        text = stringResource(id = title),
        color = titleColor,
        style = MaterialTheme.typography.h6,
        textAlign = titleAlign,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Composable
fun TopBarWithThemeSwitch(
    @StringRes title: Int,
    titleColor: Color = MaterialTheme.colors.onPrimary,
    iconColor: Color = MaterialTheme.colors.onPrimary,
    backgroundColor: Color = MaterialTheme.colors.primary,
    darkMode: Boolean,
    onThemeSwitch: () -> Unit
) {
    val themeSwitchIcon = if (darkMode) Icons.Rounded.LightMode else Icons.Rounded.DarkMode

    AppTopBar(backgroundColor = backgroundColor) {
        Text(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .weight(weight = 1F),
            text = stringResource(id = title),
            color = titleColor,
            style = MaterialTheme.typography.h6,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        IconButton(
            onClick = onThemeSwitch
        ) {
            Icon(
                imageVector = themeSwitchIcon,
                contentDescription = stringResource(id = R.string.switchAppTheme),
                tint = iconColor
            )
        }
    }
}