package tmidev.composethemeswitch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tmidev.composethemeswitch.domain.type.ThemeStyleType
import tmidev.composethemeswitch.domain.usecase.GetAppConfigurationStreamUseCase
import javax.inject.Inject

/**
 * Data class that represents the state of activity.
 */
data class MainActivityState(
    val isLoading: Boolean,
    val useDynamicColors: Boolean,
    val themeStyle: ThemeStyleType
)

/**
 * Data class that represents the state of the view model.
 */
private data class MainViewModelState(
    val isLoading: Boolean = true,
    val useDynamicColors: Boolean = true,
    val themeStyle: ThemeStyleType = ThemeStyleType.FollowAndroidSystem
) {
    fun asActivityState() = MainActivityState(
        isLoading = isLoading,
        useDynamicColors = useDynamicColors,
        themeStyle = themeStyle
    )
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAppConfigurationStreamUseCase: GetAppConfigurationStreamUseCase
) : ViewModel() {
    private val viewModelState = MutableStateFlow(value = MainViewModelState())

    val activityState = viewModelState.map { it.asActivityState() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = viewModelState.value.asActivityState()
    )

    init {
        watchAppConfigurationStream()
    }

    private fun watchAppConfigurationStream() {
        viewModelScope.launch {
            viewModelState.update { it.copy(isLoading = true) }
            delay(timeMillis = 3000)

            getAppConfigurationStreamUseCase().collectLatest { appConfiguration ->
                viewModelState.update { state ->
                    state.copy(
                        isLoading = false,
                        useDynamicColors = appConfiguration.useDynamicColors,
                        themeStyle = appConfiguration.themeStyle
                    )
                }
            }
        }
    }
}