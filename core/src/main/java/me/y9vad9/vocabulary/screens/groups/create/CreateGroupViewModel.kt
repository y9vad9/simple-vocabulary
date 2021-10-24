package me.y9vad9.vocabulary.screens.groups.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class CreateGroupViewModel : ViewModel() {
    abstract val name: StateFlow<String>
    abstract val isLoading: StateFlow<Boolean>

    abstract fun processName(name: String)

    abstract fun onBackPressed()

    abstract fun onCreateButtonClicked()
}