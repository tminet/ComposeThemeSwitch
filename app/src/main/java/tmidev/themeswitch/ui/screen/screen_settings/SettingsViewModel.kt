package tmidev.themeswitch.ui.screen.screen_settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tmidev.themeswitch.domain.usecase.IsAppThemeDarkModeUseCase
import tmidev.themeswitch.domain.usecase.UpdateAppThemeUseCase
import javax.inject.Inject

/**
 * Data class that represents the state of screen.
 */
data class SettingsScreenState(
    val isAppThemeDarkMode: Boolean?
)

/**
 * Data class that represents the state of the view model.
 */
private data class SettingsViewModelState(
    val isAppThemeDarkMode: Boolean? = null
) {
    fun asActivityState() = SettingsScreenState(
        isAppThemeDarkMode = isAppThemeDarkMode
    )
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val isAppThemeDarkModeUseCase: IsAppThemeDarkModeUseCase,
    private val updateAppThemeUseCase: UpdateAppThemeUseCase
) : ViewModel() {
    private val viewModelState = MutableStateFlow(value = SettingsViewModelState())

    val screenState = viewModelState.map { it.asActivityState() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = viewModelState.value.asActivityState()
    )

    init {
        watchAppThemeChanges()
    }

    private fun watchAppThemeChanges() {
        viewModelScope.launch {
            isAppThemeDarkModeUseCase().collectLatest { darkMode ->
                viewModelState.update { it.copy(isAppThemeDarkMode = darkMode) }
            }
        }
    }

    fun updateAppTheme(darkMode: Boolean?) {
        viewModelScope.launch {
            updateAppThemeUseCase(darkMode = darkMode)
        }
    }
}