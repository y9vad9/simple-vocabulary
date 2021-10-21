package me.y9vad9.vocabulary.screens.words

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import me.y9vad9.vocabulary.screens.AndroidxScreenNavigator
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.storage.KDSWordsStorage

class WordsScreen(activity: ComponentActivity, navController: NavController) : Screen<WordsViewModel> {
    override val viewModel: WordsViewModel by activity.viewModels {
        IntegratedWordsViewModel.Factory(
            KDSWordsStorage,
            AndroidxScreenNavigator(navController)
        )
    }

    @Composable
    override fun render() = WordsView(viewModel)
}