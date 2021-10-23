package me.y9vad9.vocabulary.screens.groups.add_translation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.WordsStorage

class IntegratedAddTranslationViewModel(
    private val groupName: String,
    private val storage: WordsStorage,
    private val navigator: ScreenNavigator
) : AddTranslationViewModel() {
    override val words: MutableStateFlow<String> = MutableStateFlow("")
    override val variants: MutableStateFlow<String> = MutableStateFlow("")

    override fun processWords(input: String) {
        words.value = input
    }

    override fun processVariants(input: String) {
        variants.value = input
    }

    override fun save() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                storage.createTranslate(
                    groupName = groupName,
                    words = words.value.split(","),
                    variants = variants.value.split(",")
                )
            }
            navigator.goBack()
        }
    }

    class Factory(
        private val groupName: String,
        private val wordsStorage: WordsStorage,
        private val navigator: ScreenNavigator
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            IntegratedAddTranslationViewModel(groupName, wordsStorage, navigator) as T
    }

}