package me.y9vad9.vocabulary.screens.groups.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.WordsStorage

class IntegratedCreateGroupViewModel(private val storage: WordsStorage, private val navigator: ScreenNavigator) : CreateGroupViewModel() {
    override val name: MutableStateFlow<String> = MutableStateFlow("")
    override val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override fun processName(name: String) {
        this.name.value = name
    }

    override fun onBackPressed() {
        navigator.goBack()
    }

    override fun onCreateButtonClicked() {
        viewModelScope.launch {
            isLoading.value = true
            withContext(Dispatchers.IO) {
                storage.createGroup(name.value)
            }
            isLoading.value = false
            navigator.goBack()
        }
    }

    class Factory(
        private val wordsStorage: WordsStorage,
        private val navigator: ScreenNavigator
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            IntegratedCreateGroupViewModel(wordsStorage, navigator) as T
    }
}