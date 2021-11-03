package me.y9vad9.vocabulary.screens.groups.items.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.WordsStorage

class IntegratedEditItemViewModel(
    private val groupName: String,
    private val id: Long,
    private val storage: WordsStorage,
    private val navigator: ScreenNavigator
) : EditItemViewModel() {
    override fun load() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                storage.getGroup(groupName).apply {
                    val current = translated.first { it.id == id }
                    words.value = current.words.joinToString(", ")
                    variants.value = current.variants.joinToString(", ")
                }
            }
        }
    }

    override fun delete() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                storage.deleteTranslate(groupName, id)
            }
            navigator.goBack()
        }
    }

    override val words: MutableStateFlow<String> = MutableStateFlow("")
    override val variants: MutableStateFlow<String> = MutableStateFlow("")

    override fun processWords(input: String) {
        words.value = input
    }

    override fun processVariants(input: String) {
        variants.value = input
    }

    override fun onBackPressed() {
        navigator.goBack()
    }

    override fun save() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                storage.editTranslate(
                    groupName = groupName,
                    id = id,
                    words = words.value.split(","),
                    variants = variants.value.split(",")
                )
            }
            navigator.goBack()
        }
    }

    class Factory(
        private val groupName: String,
        private val id: Long,
        private val wordsStorage: WordsStorage,
        private val navigator: ScreenNavigator
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            IntegratedEditItemViewModel(groupName, id, wordsStorage, navigator) as T
    }
}