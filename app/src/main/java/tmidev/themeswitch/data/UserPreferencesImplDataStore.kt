package tmidev.themeswitch.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesImplDataStore @Inject constructor(
    private val dataStorePreferences: DataStore<Preferences>
) : UserPreferences {
    private val tag = this::class.java.simpleName

    override val isAppThemeDarkMode: Flow<Boolean?> = dataStorePreferences.data
        .catch { exception ->
            exception.localizedMessage?.let { Log.e(tag, it) }
            emit(value = emptyPreferences())
        }
        .map { preferences ->
            preferences[PreferencesKeys.IS_APP_THEME_DARK_MODE]
        }

    override suspend fun updateAppTheme(darkMode: Boolean?) {
        try {
            val key = PreferencesKeys.IS_APP_THEME_DARK_MODE
            dataStorePreferences.edit { preferences ->
                if (darkMode == null) preferences.remove(key = key)
                else preferences[key] = darkMode
            }
        } catch (exception: Exception) {
            exception.localizedMessage?.let { Log.e(tag, it) }
        }
    }

    private object PreferencesKeys {
        val IS_APP_THEME_DARK_MODE = booleanPreferencesKey(name = "is_app_theme_dark_mode")
    }
}