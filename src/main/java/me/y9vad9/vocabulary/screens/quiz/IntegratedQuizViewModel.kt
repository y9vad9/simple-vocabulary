package me.y9vad9.vocabulary.screens.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.y9vad9.vocabulary.entities.Translated
import me.y9vad9.vocabulary.entities.VariantType
import me.y9vad9.vocabulary.screens.ScreenNavigator

class IntegratedQuizViewModel(
    private val groupNames: List<String>,
    private val storage: me.y9vad9.vocabulary.storage.WordsStorage,
    private val navigator: ScreenNavigator
) : QuizViewModel() {
    override val word: MutableStateFlow<String> = MutableStateFlow("")
    override val variants: MutableStateFlow<List<Pair<String, VariantType>>> = MutableStateFlow(listOf())
    override val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    override val currentQuizState: MutableStateFlow<VariantType> = MutableStateFlow(VariantType.UNSPECIFIED)
    private val currentRight: MutableStateFlow<String> = MutableStateFlow("")

    override fun loadWords() {
        isLoading.value = true
        viewModelScope.launch {
            getWords()
            isLoading.value = false
            setNewQuiz()
        }
    }

    private var groupWords: List<Translated>? = null
    private val words: List<Translated> get() = groupWords!!

    private suspend fun getWords(): List<Translated> {
        if (groupWords == null) {
            withContext(Dispatchers.IO) {
                groupWords = storage.getGroups().filter { it.name in groupNames }
                    .flatMap { it.translated }
            }
        }
        return groupWords!!
    }

    override fun onVariantChosen(variant: String): Boolean {
        val isRight = currentRight.value == variant
        variants.value = variants.value.map { (variant, _) ->
            return@map Pair(
                variant,
                if (variant == currentRight.value)
                    VariantType.CORRECT
                else VariantType.INCORRECT
            )
        }

        viewModelScope.launch {
            delay(1000L)
            currentQuizState.value = if (isRight) VariantType.CORRECT else VariantType.INCORRECT
            delay(1000L)
            setNewQuiz()
        }
        return currentRight.value == variant
    }

    private fun setNewQuiz() {
        val translated = words.random()
        word.value = translated.words.random()
        currentRight.value = translated.variants.random()
        variants.value = (randomVariants(2, translated.words) + currentRight.value)
            .shuffled()
            .map { Pair(it, VariantType.UNSPECIFIED) }
        currentQuizState.value = VariantType.UNSPECIFIED
    }

    private fun randomVariants(count: Int, right: List<String>): List<String> {
        return words.shuffled().filter { translated ->
            !translated.words.any { it in right }
        }.take(count).map { it.variants.random() }
    }

    override fun onBackPressed() {
        navigator.goBack()
    }

    class Factory(
        private val groupNames: List<String>,
        private val wordsStorage: me.y9vad9.vocabulary.storage.WordsStorage,
        private val navigator: ScreenNavigator
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            IntegratedQuizViewModel(groupNames, wordsStorage, navigator) as T
    }
}