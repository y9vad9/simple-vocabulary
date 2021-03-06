package me.y9vad9.vocabulary.screens.quiz

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.screens.ScreenNavigator

class QuizScreen(private val groupNames: List<String>, private val navigator: ScreenNavigator) : Screen<QuizViewModel> {
    override val viewModel: QuizViewModel
        @Composable get() = viewModel(
            factory = IntegratedQuizViewModel.Factory(
                groupNames,
                me.y9vad9.storage.kds.KDSWordsStorage,
                navigator
            )
        )

    @Composable
    override fun render() = QuizView(viewModel)
}