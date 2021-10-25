package me.y9vad9.vocabulary.screens.groups.items.add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class AddTranslationViewModel : ViewModel() {
    abstract val words: StateFlow<String>
    abstract val variants: StateFlow<String>

    abstract fun processWords(input: String)
    abstract fun processVariants(input: String)

    abstract fun onBackPressed()

    abstract fun save()
}