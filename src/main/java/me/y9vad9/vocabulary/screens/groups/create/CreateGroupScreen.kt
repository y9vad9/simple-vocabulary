package me.y9vad9.vocabulary.screens.groups.create

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.KDSWordsStorage

class CreateGroupScreen(private val screenNavigator: ScreenNavigator) : Screen<CreateGroupViewModel> {
    override val viewModel: CreateGroupViewModel
        @Composable get() = viewModel(
            factory = IntegratedCreateGroupViewModel.Factory(
                KDSWordsStorage,
                screenNavigator
            )
        )

    @Composable
    override fun render() = CreateGroupView(viewModel)
}