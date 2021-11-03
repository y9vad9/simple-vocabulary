package me.y9vad9.vocabulary.screens.groups.items.add

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.KDSWordsStorage

class AddTranslationScreen(
    private val groupName: String,
    private val navigator: ScreenNavigator
) : Screen<AddTranslationViewModel> {
    override val viewModel: AddTranslationViewModel
        @Composable get() = viewModel(
            factory = IntegratedAddTranslationViewModel.Factory(
                groupName,
                KDSWordsStorage,
                navigator
            )
        )

    @Composable
    override fun render() = AddTranslationView(viewModel)
}