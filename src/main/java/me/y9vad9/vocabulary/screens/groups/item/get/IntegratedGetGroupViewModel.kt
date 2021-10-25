package me.y9vad9.vocabulary.screens.groups.item.get

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.y9vad9.vocabulary.entities.Translated
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.screens.groups.view.GetGroupViewModel
import me.y9vad9.vocabulary.storage.WordsStorage

class IntegratedGetGroupViewModel(
    private val groupName: String,
    private val storage: WordsStorage,
    private val navigator: ScreenNavigator
) : GetGroupViewModel() {
    override val words: MutableStateFlow<List<Translated>> = MutableStateFlow(emptyList())
    override val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override fun loadWords() {
        isLoading.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                words.value = storage.getGroup(groupName).translated
            }
            isLoading.value = false
        }
    }

    override fun onBackPressed() {
        navigator.goBack()
    }

    override fun onTranslatedClicked(id: Long) {

    }

    override fun onAddButtonClicked() {
        navigator.gotoAddingWord(groupName)
    }

    class Factory(
        private val groupName: String, private val storage: WordsStorage, private val navigator: ScreenNavigator
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            IntegratedGetGroupViewModel(groupName, storage, navigator) as T
    }
}