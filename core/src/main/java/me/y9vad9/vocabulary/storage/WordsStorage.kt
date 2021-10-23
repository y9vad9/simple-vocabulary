package me.y9vad9.vocabulary.storage

import me.y9vad9.vocabulary.entities.TranslatedGroup

interface WordsStorage {
    suspend fun getGroups(): List<TranslatedGroup>
    suspend fun getGroup(groupName: String): TranslatedGroup
    suspend fun createGroup(name: String)
    suspend fun deleteGroup(name: String)
    suspend fun createTranslate(groupName: String, words: List<String>, variants: List<String>)
    suspend fun deleteTranslate(groupName: String, id: Long)
}