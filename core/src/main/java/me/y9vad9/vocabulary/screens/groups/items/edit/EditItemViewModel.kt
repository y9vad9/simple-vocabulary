package me.y9vad9.vocabulary.screens.groups.items.edit

import me.y9vad9.vocabulary.screens.groups.items.add.AddTranslationViewModel

abstract class EditItemViewModel : AddTranslationViewModel() {
    abstract fun load()
    abstract fun delete()
}