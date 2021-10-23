package me.y9vad9.vocabulary.screens.groups.create

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.KDSWordsStorage

class CreateGroupScreen(activity: ComponentActivity, screenNavigator: ScreenNavigator) : Screen<CreateGroupViewModel> {
    override val viewModel: CreateGroupViewModel by activity.viewModels {
        IntegratedCreateGroupViewModel.Factory(
            KDSWordsStorage,
            screenNavigator
        )
    }

    @Composable
    override fun render() = CreateGroupView(viewModel)
}