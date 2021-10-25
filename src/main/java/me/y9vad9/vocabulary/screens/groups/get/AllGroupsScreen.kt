package me.y9vad9.vocabulary.screens.groups.get

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.KDSWordsStorage

class AllGroupsScreen(activity: ComponentActivity, navigator: ScreenNavigator) : Screen<AllGroupsViewModel> {
    override val viewModel: AllGroupsViewModel by activity.viewModels {
        IntegratedAllGroupsViewModel.Factory(
            KDSWordsStorage,
            navigator
        )
    }

    @Composable
    override fun render() = AllGroupsView(viewModel)
}