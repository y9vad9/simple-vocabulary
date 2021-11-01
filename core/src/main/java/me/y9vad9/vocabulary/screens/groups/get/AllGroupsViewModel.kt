package me.y9vad9.vocabulary.screens.groups.get

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import me.y9vad9.vocabulary.entities.Selectable
import me.y9vad9.vocabulary.entities.TranslatedGroup

abstract class AllGroupsViewModel : ViewModel() {
    abstract val groups: StateFlow<List<Selectable<TranslatedGroup>>>
    abstract val isLoading: StateFlow<Boolean>
    abstract val isAnyItemSelected: StateFlow<Boolean>
    abstract val isCanPlay: StateFlow<Boolean>

    abstract fun loadWords()

    abstract fun onTranslatedGroupPressed(groupName: String)
    abstract fun onAddTranslatedItemPressed(groupName: String)
    abstract fun onDeleteGroupsClicked()
    abstract fun onPlayButtonPressed()
    abstract fun onCreateGroupPressed()
    abstract fun selectItem(groupName: String)
}