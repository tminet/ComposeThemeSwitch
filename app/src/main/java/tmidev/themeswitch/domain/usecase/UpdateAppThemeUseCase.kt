package tmidev.themeswitch.domain.usecase

interface UpdateAppThemeUseCase {
    suspend operator fun invoke(darkMode: Boolean)
}