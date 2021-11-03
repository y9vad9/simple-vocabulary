package me.y9vad9.vocabulary.screens.groups.items.get

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.KDSWordsStorage

class GetGroupItemsScreen(private val groupName: String, private val navigator: ScreenNavigator) : Screen<GetGroupViewModel> {
    override val viewModel: GetGroupViewModel
        @Composable get() = viewModel(
            factory = IntegratedGetGroupViewModel.Factory(groupName, KDSWordsStorage, navigator)
        )

    @Composable
    override fun render() = ViewTranslationView(viewModel)
}