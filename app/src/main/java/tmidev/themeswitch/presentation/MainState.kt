package tmidev.themeswitch.presentation

import tmidev.themeswitch.domain.model.Post

data class MainState(
    val isLoading: Boolean = true,
    val isAppThemeDarkMode: Boolean? = null,
    val posts: List<Post> = emptyList()
)