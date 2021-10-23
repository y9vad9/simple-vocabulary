package me.y9vad9.vocabulary.screens.groups.add_translation

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.KDSWordsStorage

class AddTranslationScreen(
    private val groupName: String,
    activity: ComponentActivity,
    private val navigator: ScreenNavigator
) : Screen<AddTranslationViewModel> {
    override val viewModel: AddTranslationViewModel by activity.viewModels {
        IntegratedAddTranslationViewModel.Factory(
            groupName,
            KDSWordsStorage,
            navigator
        )
    }

    @Composable
    override fun render() = AddTranslationView(viewModel)
}