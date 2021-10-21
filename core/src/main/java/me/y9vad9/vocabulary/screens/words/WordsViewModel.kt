package me.y9vad9.vocabulary.screens.words

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import me.y9vad9.vocabulary.entities.Translated
import me.y9vad9.vocabulary.entities.TranslatedGroup

abstract class WordsViewModel : ViewModel() {
    abstract val words: StateFlow<List<TranslatedGroup>>
    abstract val isLoading: StateFlow<Boolean>

    abstract fun loadWords()

    abstract fun onTranslatedItemPressed(translated: Translated)
    abstract fun onAddTranslatedItemPressed(groupName: String)
    abstract fun onCreateGroupPressed()
}