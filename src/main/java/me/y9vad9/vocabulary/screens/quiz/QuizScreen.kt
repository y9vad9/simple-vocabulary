package me.y9vad9.vocabulary.screens.quiz

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.KDSWordsStorage

class QuizScreen(groupNames: List<String>, activity: ComponentActivity, navigator: ScreenNavigator) : Screen<QuizViewModel> {
    override val viewModel: QuizViewModel by activity.viewModels {
        IntegratedQuizViewModel.Factory(
            groupNames,
            KDSWordsStorage,
            navigator
        )
    }

    @Composable
    override fun render() = QuizView(viewModel)
}