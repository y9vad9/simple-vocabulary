package me.y9vad9.vocabulary.screens.groups.items.get

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.y9vad9.vocabulary.entities.Translated
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.WordsStorage

class IntegratedGetGroupViewModel(
    private val groupName: String,
    private val storage: WordsStorage,
    private val navigator: ScreenNavigator
) : GetGroupViewModel() {
    override val words: MutableStateFlow<List<Translated>> = MutableStateFlow(emptyList())
    override val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isPlayButtonEnabled: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override fun loadWords() {
        isLoading.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                words.value = storage.getGroup(groupName).translated
                if (words.value.size > 3)
                    isPlayButtonEnabled.value = true
            }
            isLoading.value = false
        }
    }

    override fun onBackPressed() {
        navigator.goBack()
    }

    override fun onTranslatedClicked(id: Long) {
        navigator.editGroupTranslation(groupName, id)
    }

    override fun onAddButtonClicked() {
        navigator.gotoAddingWord(groupName)
    }

    override fun onPlayButtonClicked() {
        navigator.gotoQuiz(listOf(groupName))
    }

    class Factory(
        private val groupName: String, private val storage: WordsStorage, private val navigator: ScreenNavigator
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            IntegratedGetGroupViewModel(groupName, storage, navigator) as T
    }
}