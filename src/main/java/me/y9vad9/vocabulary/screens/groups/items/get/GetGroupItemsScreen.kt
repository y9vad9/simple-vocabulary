package me.y9vad9.vocabulary.screens.groups.items.get

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.KDSWordsStorage

class GetGroupItemsScreen(activity: ComponentActivity, groupName: String, navigator: ScreenNavigator) : Screen<GetGroupViewModel> {
    override val viewModel: GetGroupViewModel by activity.viewModels {
        IntegratedGetGroupViewModel.Factory(groupName, KDSWordsStorage, navigator)
    }

    @Composable
    override fun render() = ViewTranslationView(viewModel)
}