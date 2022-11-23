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
import tmidev.themeswitch.domain.type.ThemeStyleType
import tmidev.themeswitch.domain.usecase.ChangeThemeStyleUseCase
import tmidev.themeswitch.domain.usecase.GetAppConfigurationStreamUseCase
import tmidev.themeswitch.domain.usecase.ToggleDynamicColorsUseCase
import javax.inject.Inject

/**
 * Data class that represents the state of screen.
 */
data class SettingsScreenState(
    val useDynamicColors: Boolean,
    val themeStyle: ThemeStyleType
)

/**
 * Data class that represents the state of the view model.
 */
private data class SettingsViewModelState(
    val useDynamicColors: Boolean = true,
    val themeStyle: ThemeStyleType = ThemeStyleType.FollowAndroidSystem
) {
    fun asScreenState() = SettingsScreenState(
        useDynamicColors = useDynamicColors,
        themeStyle = themeStyle
    )
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getAppConfigurationStreamUseCase: GetAppConfigurationStreamUseCase,
    private val toggleDynamicColorsUseCase: ToggleDynamicColorsUseCase,
    private val changeThemeStyleUseCase: ChangeThemeStyleUseCase
) : ViewModel() {
    private val viewModelState = MutableStateFlow(value = SettingsViewModelState())

    val screenState = viewModelState.map { it.asScreenState() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = viewModelState.value.asScreenState()
    )

    init {
        watchAppConfigurationStream()
    }

    private fun watchAppConfigurationStream() {
        viewModelScope.launch {
            getAppConfigurationStreamUseCase().collectLatest { appConfiguration ->
                viewModelState.update { state ->
                    state.copy(
                        useDynamicColors = appConfiguration.useDynamicColors,
                        themeStyle = appConfiguration.themeStyle
                    )
                }
            }
        }
    }

    fun toggleDynamicColors() {
        viewModelScope.launch {
            toggleDynamicColorsUseCase()
        }
    }

    fun changeThemeStyle(themeStyle: ThemeStyleType) {
        viewModelScope.launch {
            changeThemeStyleUseCase(themeStyle = themeStyle)
        }
    }
}