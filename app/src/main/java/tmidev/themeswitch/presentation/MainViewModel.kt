package tmidev.themeswitch.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tmidev.themeswitch.domain.model.Post
import tmidev.themeswitch.domain.usecase.IsAppThemeDarkModeUseCase
import tmidev.themeswitch.domain.usecase.UpdateAppThemeUseCase
import tmidev.themeswitch.util.PostFactory
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isAppThemeDarkModeUseCase: IsAppThemeDarkModeUseCase,
    private val updateAppThemeUseCase: UpdateAppThemeUseCase
) : ViewModel() {
    private val _isAppThemeDarkMode = mutableStateOf<Boolean?>(value = null)
    val isAppThemeDarkMode: State<Boolean?> get() = _isAppThemeDarkMode

    private val _postList = mutableStateOf(
        value = PostFactory.createPostList(size = (20..50).random())
    )
    val postList: State<List<Post>> get() = _postList

    init {
        getAppTheme()
    }

    private fun getAppTheme() = viewModelScope.launch {
        isAppThemeDarkModeUseCase().collectLatest { appTheme ->
            _isAppThemeDarkMode.value = appTheme
        }
    }

    fun updateAppTheme(darkMode: Boolean) = viewModelScope.launch {
        updateAppThemeUseCase(darkMode = darkMode)
    }
}