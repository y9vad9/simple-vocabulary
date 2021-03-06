package me.y9vad9.vocabulary.screens.groups.items.get

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import me.y9vad9.vocabulary.entities.Translated

abstract class GetGroupViewModel : ViewModel() {
    abstract val words: StateFlow<List<Translated>>
    abstract val isLoading: StateFlow<Boolean>
    abstract val isPlayButtonEnabled: StateFlow<Boolean>

    abstract fun loadWords()

    abstract fun onBackPressed()

    abstract fun onTranslatedClicked(id: Long)
    abstract fun onAddButtonClicked()
    abstract fun onPlayButtonClicked()
}