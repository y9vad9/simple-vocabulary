package me.y9vad9.storage.kds

import `fun`.kotlingang.kds.KSharedDataStorage
import `fun`.kotlingang.kds.annotation.ExperimentalKDSApi
import `fun`.kotlingang.kds.delegate.storageList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.y9vad9.vocabulary.entities.Translated
import me.y9vad9.vocabulary.entities.TranslatedGroup
import me.y9vad9.vocabulary.storage.WordsStorage

object KDSWordsStorage : WordsStorage {
    private object KDSStorage : KSharedDataStorage() {
        @OptIn(ExperimentalKDSApi::class)
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

    override suspend fun createTranslate(groupName: String, words: List<String>, variants: List<String>) {
        val index = KDSStorage.groups.indexOfFirst { it.name == groupName }
        val group = KDSStorage.groups[index]
        KDSStorage.groups[index] = group.copy(
            translated = group.translated.toMutableList().apply {
                add(Translated((group.translated.maxOfOrNull { it.id } ?: 0) + 1, words, variants))
            }
        )
    }

    override suspend fun editTranslate(groupName: String, id: Long, words: List<String>, variants: List<String>) {
        val index = KDSStorage.groups.indexOfFirst { it.name == groupName }
        val group = KDSStorage.groups[index]
        val translateIndex = group.translated.indexOfFirst { it.id == id }
        val list = group.translated.toMutableList().apply {
            this[translateIndex] = this[translateIndex].copy(words = words, variants = variants)
        }
        KDSStorage.groups[index] = group.copy(groupName, list)
    }

    override suspend fun deleteTranslate(groupName: String, id: Long) {
        val index = KDSStorage.groups.indexOfFirst { it.name == groupName }
        val group = KDSStorage.groups[index]
        KDSStorage.groups[index] =
            group.copy(translated = group.translated.toMutableList().apply { removeAll { it.id == id } })
    }
}