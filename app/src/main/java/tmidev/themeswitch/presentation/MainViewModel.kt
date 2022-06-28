package tmidev.themeswitch.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tmidev.themeswitch.domain.usecase.IsAppThemeDarkModeUseCase
import tmidev.themeswitch.domain.usecase.UpdateAppThemeUseCase
import tmidev.themeswitch.util.PostFactory
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isAppThemeDarkModeUseCase: IsAppThemeDarkModeUseCase,
    private val updateAppThemeUseCase: UpdateAppThemeUseCase
) : ViewModel() {
    var state by mutableStateOf(value = MainState())
        private set

    private val staticPosts = PostFactory.createPostList(size = (20..40).random())

    init {
        loadAppConfig()
    }

    private fun loadAppConfig() = viewModelScope.launch {
        isAppThemeDarkModeUseCase().collectLatest { isDarkTheme ->
            state = state.copy(
                isAppThemeDarkMode = isDarkTheme,
                posts = staticPosts
            )
            delayToShowCustomAnimationOnAppStartup()
        }
    }

    private suspend fun delayToShowCustomAnimationOnAppStartup() {
        if (state.isLoading) {
            delay(timeMillis = 2000)
            state = state.copy(isLoading = false)
        }
    }

    fun updateAppTheme(darkMode: Boolean) = viewModelScope.launch {
        updateAppThemeUseCase(darkMode = darkMode)
    }
}