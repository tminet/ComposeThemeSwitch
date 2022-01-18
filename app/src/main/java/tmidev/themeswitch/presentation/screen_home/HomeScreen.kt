package tmidev.themeswitch.presentation.screen_home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import tmidev.themeswitch.R
import tmidev.themeswitch.domain.model.Post
import tmidev.themeswitch.presentation.MainViewModel
import tmidev.themeswitch.presentation.common.TopBarWithThemeSwitch
import tmidev.themeswitch.presentation.theme.elevating
import tmidev.themeswitch.presentation.theme.spacing

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel
) {
    val isAppThemeDarkMode = mainViewModel.isAppThemeDarkMode.value ?: isSystemInDarkTheme()
    val postList = mainViewModel.postList.value
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBarWithThemeSwitch(
            title = R.string.titleHomeScreen,
            darkMode = isAppThemeDarkMode
        ) {
            mainViewModel.updateAppTheme(darkMode = !isAppThemeDarkMode)
        }
        LazyColumn(
            contentPadding = PaddingValues(MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            modifier = Modifier.fillMaxWidth()
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
    val expandIcon = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore
    Card(
        shape = MaterialTheme.shapes.large,
        elevation = MaterialTheme.elevating.none,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacing.medium)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = post.title,
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.small)
                        .weight(weight = 1F)
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
            if (expanded) Text(
                text = post.post,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium)
            )
        }
    }
}