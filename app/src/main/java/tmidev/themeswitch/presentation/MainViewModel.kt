package tmidev.themeswitch.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
    private val _state = mutableStateOf(value = MainState())
    val state: State<MainState> get() = _state

    private val staticPosts = PostFactory.createPostList(size = (20..40).random())

    init {
        loadAppConfigState()
    }

    private fun loadAppConfigState() = viewModelScope.launch {
        isAppThemeDarkModeUseCase().collectLatest { isDarkTheme ->
            _state.value = _state.value.copy(
                isAppThemeDarkMode = isDarkTheme,
                posts = staticPosts
            )
            delayToShowCustomAnimationOnAppStartup()
        }
    }

    private suspend fun delayToShowCustomAnimationOnAppStartup() {
        if (_state.value.isLoading) {
            delay(timeMillis = 2000)
            _state.value = _state.value.copy(isLoading = false)
        }
    }

    fun updateAppTheme(darkMode: Boolean) = viewModelScope.launch {
        updateAppThemeUseCase(darkMode = darkMode)
    }
}