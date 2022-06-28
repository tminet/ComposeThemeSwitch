package tmidev.themeswitch.presentation.screen_home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import tmidev.themeswitch.R
import tmidev.themeswitch.domain.model.Post
import tmidev.themeswitch.presentation.MainViewModel
import tmidev.themeswitch.presentation.common.AppTopBarWithThemeSwitch
import tmidev.themeswitch.presentation.common.theme.elevating
import tmidev.themeswitch.presentation.common.theme.spacing

@Composable
fun HomeScreen(mainViewModel: MainViewModel) {
    val state = mainViewModel.state

    val isAppThemeDarkMode = state.isAppThemeDarkMode ?: isSystemInDarkTheme()
    val postList = state.posts

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppTopBarWithThemeSwitch(
                title = R.string.titleHomeScreen,
                darkMode = isAppThemeDarkMode
            ) {
                mainViewModel.updateAppTheme(darkMode = !isAppThemeDarkMode)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            items(items = postList) { post ->
                ComposePostItem(post = post)
            }
        }
    }
}

@Composable
private fun ComposePostItem(post: Post) {
    var expanded by remember { mutableStateOf(value = false) }
    val expandIcon = if (expanded) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        elevation = MaterialTheme.elevating.none,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialTheme.spacing.small,
                            vertical = MaterialTheme.spacing.none
                        )
                        .weight(weight = 1F),
                    text = post.title,
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
                        imageVector = expandIcon,
                        contentDescription = expandIcon.name,
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            }

            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(animationSpec = tween()) +
                        expandVertically(animationSpec = tween(durationMillis = 400)),
                exit = fadeOut(animationSpec = tween()) +
                        shrinkVertically(animationSpec = tween(durationMillis = 400))
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.spacing.medium,
                            vertical = MaterialTheme.spacing.none
                        ),
                    text = post.post,
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}