package tmidev.themeswitch.ui

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
import tmidev.themeswitch.domain.usecase.IsAppThemeDarkModeUseCase
import javax.inject.Inject

/**
 * Data class that represents the state of activity.
 */
data class MainActivityState(
    val isLoading: Boolean,
    val isAppThemeDarkMode: Boolean?
)

/**
 * Data class that represents the state of the view model.
 */
private data class MainViewModelState(
    val isLoading: Boolean = true,
    val isAppThemeDarkMode: Boolean? = null
) {
    fun asActivityState() = MainActivityState(
        isLoading = isLoading,
        isAppThemeDarkMode = isAppThemeDarkMode
    )
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isAppThemeDarkModeUseCase: IsAppThemeDarkModeUseCase
) : ViewModel() {
    private val viewModelState = MutableStateFlow(value = MainViewModelState())

    val activityState = viewModelState.map { it.asActivityState() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = viewModelState.value.asActivityState()
    )

    init {
        watchAppThemeChanges()
    }

    private fun watchAppThemeChanges() {
        viewModelScope.launch {
            viewModelState.update { it.copy(isLoading = true) }
            delay(timeMillis = 3000)

            isAppThemeDarkModeUseCase().collectLatest { darkMode ->
                viewModelState.update { state ->
                    state.copy(
                        isLoading = false,
                        isAppThemeDarkMode = darkMode
                    )
                }
            }
        }
    }
}