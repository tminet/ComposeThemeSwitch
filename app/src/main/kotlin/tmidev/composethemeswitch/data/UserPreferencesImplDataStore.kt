package tmidev.composethemeswitch.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import tmidev.composethemeswitch.domain.model.AppConfiguration
import tmidev.composethemeswitch.domain.type.ThemeStyleType
import javax.inject.Inject

class UserPreferencesImplDataStore @Inject constructor(
    private val dataStorePreferences: DataStore<Preferences>
) : UserPreferences {
    private val tag = this::class.java.simpleName

    override val appConfigurationStream: Flow<AppConfiguration> = dataStorePreferences.data
        .catch { exception ->
            exception.localizedMessage?.let { Log.e(tag, it) }
            emit(value = emptyPreferences())
        }
        .map { preferences ->
            val useDynamicColors = preferences[PreferencesKeys.useDynamicColors] ?: true
            val themeStyle = preferences[PreferencesKeys.themeStyle].toThemeStyleType()

            AppConfiguration(
                useDynamicColors = useDynamicColors,
                themeStyle = themeStyle
            )
        }

    override suspend fun toggleDynamicColors() {
        tryIt {
            dataStorePreferences.edit { preferences ->
                val current = preferences[PreferencesKeys.useDynamicColors] ?: true
                preferences[PreferencesKeys.useDynamicColors] = !current
            }
        }
    }

    override suspend fun changeThemeStyle(themeStyle: ThemeStyleType) {
        tryIt {
            dataStorePreferences.edit { preferences ->
                preferences[PreferencesKeys.themeStyle] = themeStyle.name
            }
        }
    }

    private suspend fun tryIt(action: suspend () -> Unit) {
        try {
            action()
        } catch (exception: Exception) {
            exception.localizedMessage?.let { Log.e(tag, it) }
        }
    }

    private fun String?.toThemeStyleType(): ThemeStyleType = when (this) {
        ThemeStyleType.LightMode.name -> ThemeStyleType.LightMode
        ThemeStyleType.DarkMode.name -> ThemeStyleType.DarkMode
        else -> ThemeStyleType.FollowAndroidSystem
    }

    private object PreferencesKeys {
        val useDynamicColors = booleanPreferencesKey(name = "use_dynamic_colors")
        val themeStyle = stringPreferencesKey(name = "theme_style")
    }
}