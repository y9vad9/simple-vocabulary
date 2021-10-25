package me.y9vad9.vocabulary.screens.groups.get

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.y9vad9.vocabulary.entities.TranslatedGroup
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.WordsStorage

class IntegratedAllGroupsViewModel(
    private val wordsStorage: WordsStorage,
    private val navigator: ScreenNavigator
) : AllGroupsViewModel() {
    override val words: MutableStateFlow<List<TranslatedGroup>> = MutableStateFlow(emptyList())
    override val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)

    override fun loadWords() {
        viewModelScope.launch {
            isLoading.value = false
            words.value = wordsStorage.getGroups()
        }
    }

    override fun onTranslatedGroupPressed(groupName: String) {
        navigator.gotoWordsList(groupName)
    }

    override fun onAddTranslatedItemPressed(groupName: String) {
        navigator.gotoAddingWord(groupName)
    }

    override fun onCreateGroupPressed() {
        navigator.gotoGroupCreation()
    }

    class Factory(
        private val wordsStorage: WordsStorage,
        private val navigator: ScreenNavigator
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            IntegratedAllGroupsViewModel(wordsStorage, navigator) as T
    }

}