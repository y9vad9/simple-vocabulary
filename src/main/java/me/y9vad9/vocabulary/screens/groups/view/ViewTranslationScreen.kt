package me.y9vad9.vocabulary.screens.groups.view

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.KDSWordsStorage

class ViewTranslationScreen(activity: ComponentActivity, groupName: String, navigator: ScreenNavigator) : Screen<ViewTranslationViewModel> {
    override val viewModel: ViewTranslationViewModel by activity.viewModels {
        IntegratedViewTranslationViewModel.Factory(groupName, KDSWordsStorage, navigator)
    }

    @Composable
    override fun render() = ViewTranslationView(viewModel)
}