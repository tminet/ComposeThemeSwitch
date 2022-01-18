package tmidev.themeswitch.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tmidev.themeswitch.data.UserPreferences
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {
    private val _isAppThemeDarkMode = mutableStateOf<Boolean?>(value = null)
    val isAppThemeDarkMode: State<Boolean?> get() = _isAppThemeDarkMode

    init {
        getAppTheme()
    }

    private fun getAppTheme() = viewModelScope.launch {
        userPreferences.isAppThemeDarkMode.collectLatest { appTheme ->
            _isAppThemeDarkMode.value = appTheme
        }
    }

    fun updateAppTheme(darkMode: Boolean) = viewModelScope.launch {
        userPreferences.updateAppTheme(darkMode = darkMode)
    }
}