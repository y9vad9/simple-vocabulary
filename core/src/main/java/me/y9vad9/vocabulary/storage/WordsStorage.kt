package me.y9vad9.vocabulary.storage

import me.y9vad9.vocabulary.entities.TranslatedGroup
import me.y9vad9.vocabulary.entities.Word

interface WordsStorage {
    suspend fun getGroups(): List<TranslatedGroup>
    suspend fun getGroup(groupName: String): TranslatedGroup
    suspend fun createGroup(name: String)
    suspend fun deleteGroup(name: String)
    suspend fun createTranslate(groupName: String, words: List<Word>, variants: List<Word>)
    suspend fun deleteTranslate(groupName: String, id: Long)
}