package me.y9vad9.vocabulary.storage

import `fun`.kotlingang.kds.KSharedDataStorage
import `fun`.kotlingang.kds.delegate.storageList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.y9vad9.vocabulary.entities.Translated
import me.y9vad9.vocabulary.entities.TranslatedGroup
import me.y9vad9.vocabulary.entities.Word

object KDSWordsStorage : WordsStorage {
    private object KDSStorage : KSharedDataStorage() {
        val groups: MutableList<TranslatedGroup> by storageList { mutableListOf() }
    }

    override suspend fun getGroups(): List<TranslatedGroup> = withContext(Dispatchers.IO) {
        return@withContext KDSStorage.groups
    }

    override suspend fun getGroup(groupName: String): TranslatedGroup = withContext(Dispatchers.IO) {
        return@withContext KDSStorage.groups.first { it.name == groupName }
    }

    override suspend fun createGroup(name: String) {
        KDSStorage.groups.add(TranslatedGroup(name, listOf()))
    }

    override suspend fun deleteGroup(name: String) {
        KDSStorage.groups.removeAll { it.name == name }
    }

    override suspend fun createTranslate(groupName: String, words: List<Word>, variants: List<Word>) {
        val index = KDSStorage.groups.indexOfFirst { it.name == groupName }
        val group = KDSStorage.groups[index]
        KDSStorage.groups[index] = group.copy(
            translated = group.translated.toMutableList().apply {
                add(Translated(group.translated.maxOf { it.id } + 1, words, variants))
            }
        )
    }

    override suspend fun deleteTranslate(groupName: String, id: Long) {
        val index = KDSStorage.groups.indexOfFirst { it.name == groupName }
        val group = KDSStorage.groups[index]
        KDSStorage.groups[index] =
            group.copy(translated = group.translated.toMutableList().apply { removeAll { it.id == id } })
    }
}