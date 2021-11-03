package me.y9vad9.vocabulary.screens.groups.get

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.KDSWordsStorage

class AllGroupsScreen(private val navigator: ScreenNavigator) : Screen<AllGroupsViewModel> {
    override val viewModel: AllGroupsViewModel
        @Composable get() = viewModel(
            factory = IntegratedAllGroupsViewModel.Factory(
                KDSWordsStorage,
                navigator
            )
        )

    @Composable
    override fun render() = AllGroupsView(viewModel)
}