package me.y9vad9.vocabulary.screens.groups.get

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.y9vad9.vocabulary.entities.MutableSelectable
import me.y9vad9.vocabulary.entities.Selectable
import me.y9vad9.vocabulary.entities.TranslatedGroup
import me.y9vad9.vocabulary.entities.opposite
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.WordsStorage

class IntegratedAllGroupsViewModel private constructor(
    private val wordsStorage: WordsStorage,
    private val navigator: ScreenNavigator
) : AllGroupsViewModel() {
    override val groups: MutableStateFlow<List<Selectable<TranslatedGroup>>> = MutableStateFlow(emptyList())
    override val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    override val isAnyItemSelected: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isCanPlay: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override fun loadWords() {
        viewModelScope.launch {
            isLoading.value = false
            groups.value = wordsStorage.getGroups().map { MutableSelectable(it, false) }
        }
    }

    override fun onTranslatedGroupPressed(groupName: String) {
        if (!isAnyItemSelected.value)
            navigator.gotoWordsList(groupName)
        else selectItem(groupName)
    }

    override fun onAddTranslatedItemPressed(groupName: String) {
        navigator.gotoAddingWord(groupName)
    }

    override fun onDeleteGroupsClicked() {
        isLoading.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                groups.value.filter { it.isSelected }.map { it.data.name }.forEach {
                    wordsStorage.deleteGroup(it)
                }
            }
            groups.value = wordsStorage.getGroups().map { MutableSelectable(it, false) }
        }
        isAnyItemSelected.value = false
        isLoading.value = false
    }

    override fun onPlayButtonPressed() {
        navigator.gotoQuiz(groups.value.filter { it.isSelected }.map { it.data.name })
        isAnyItemSelected.value = false
        groups.value = groups.value.map { if (it.isSelected) it.opposite() else it }
    }

    override fun onCreateGroupPressed() {
        navigator.gotoGroupCreation()
    }

    override fun selectItem(groupName: String) {
        groups.value = groups.value.toMutableList().apply {
            val index = indexOfFirst { it.data.name == groupName }
            val element = groups.value[index].opposite()
            this[index] = element
        }.toList()
        isAnyItemSelected.value = groups.value.any { it.isSelected }
        isCanPlay.value = groups.value.flatMap { it.data.translated }.count() > 3
    }

    class Factory(
        private val wordsStorage: WordsStorage,
        private val navigator: ScreenNavigator
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            IntegratedAllGroupsViewModel(wordsStorage, navigator) as T
    }

}