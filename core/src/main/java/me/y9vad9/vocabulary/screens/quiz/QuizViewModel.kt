package me.y9vad9.vocabulary.screens.quiz

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import me.y9vad9.vocabulary.entities.VariantType

abstract class QuizViewModel : ViewModel() {
    abstract val word: StateFlow<String>
    abstract val variants: StateFlow<List<Pair<String, VariantType>>>
    abstract val isLoading: StateFlow<Boolean>
    abstract val currentQuizState: StateFlow<VariantType>

    abstract fun loadWords()

    abstract fun onVariantChosen(variant: String): Boolean
    abstract fun onBackPressed()
}