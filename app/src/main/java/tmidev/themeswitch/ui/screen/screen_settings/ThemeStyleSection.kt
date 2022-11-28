package tmidev.themeswitch.ui.screen.screen_settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tmidev.themeswitch.R
import tmidev.themeswitch.domain.type.ThemeStyleType
import tmidev.themeswitch.util.AppIcons

/**
 * Compose the options to change theme style.
 *
 * @param modifier the [Modifier] to apply on container of this composable.
 * @param themeStyle the current selected [ThemeStyleType].
 * @param changeThemeStyle callback with the new selected [ThemeStyleType].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeStyleSection(
    modifier: Modifier = Modifier,
    themeStyle: ThemeStyleType,
    changeThemeStyle: (ThemeStyleType) -> Unit
) = Column(modifier = modifier) {
    InputChip(
        selected = themeStyle == ThemeStyleType.FollowAndroidSystem,
        onClick = {
            if (themeStyle != ThemeStyleType.FollowAndroidSystem)
                changeThemeStyle(ThemeStyleType.FollowAndroidSystem)
        },
        label = { Text(text = stringResource(id = R.string.followAndroidSystem)) },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(size = AssistChipDefaults.IconSize),
                painter = painterResource(id = AppIcons.Android),
                contentDescription = null
            )
        }
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InputChip(
            selected = themeStyle == ThemeStyleType.LightMode,
            onClick = {
                if (themeStyle != ThemeStyleType.LightMode)
                    changeThemeStyle(ThemeStyleType.LightMode)
            },
            label = { Text(text = stringResource(id = R.string.lightMode)) },
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(size = AssistChipDefaults.IconSize),
                    painter = painterResource(id = AppIcons.LightMode),
                    contentDescription = null
                )
            }
        )

        InputChip(
            selected = themeStyle == ThemeStyleType.DarkMode,
            onClick = {
                if (themeStyle != ThemeStyleType.DarkMode)
                    changeThemeStyle(ThemeStyleType.DarkMode)
            },
            label = { Text(text = stringResource(id = R.string.darkMode)) },
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(size = AssistChipDefaults.IconSize),
                    painter = painterResource(id = AppIcons.DarkMode),
                    contentDescription = null
                )
            }
        )
    }
}