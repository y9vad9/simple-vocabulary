package me.y9vad9.vocabulary.storage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.y9vad9.vocabulary.entities.Translated
import me.y9vad9.vocabulary.entities.TranslatedGroup
import me.y9vad9.vocabulary.storage.WordsStorage

class RoomWordsStorage internal constructor(
    private val groupsDAO: TranslatedGroupsDAO,
    private val variantsDAO: TranslatedVariantsDAO,
    private val wordsDAO: TranslatedWordsDAO,
    private val wordVariantsDAO: WordVariantsDAO
) : WordsStorage {

    companion object Factory {
        fun getInstance(context: Context): RoomWordsStorage {
            val appDb = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "vocabulary"
            ).build()
            return RoomWordsStorage(
                appDb.translatedGroups(),
                appDb.translatedVariants(),
                appDb.translatedWordsDao(),
                appDb.wordVariantsDao()
            )
        }
    }

    override suspend fun getGroups(): List<TranslatedGroup> {
        return groupsDAO.getAll().map { it.toTranslatedGroup() }
    }

    override suspend fun getGroup(groupName: String): TranslatedGroup {
        return groupsDAO.get(groupName).toTranslatedGroup()
    }

    override suspend fun createGroup(name: String) {
        return groupsDAO.insert(GroupEntity(name))
    }

    override suspend fun deleteGroup(name: String) {
        groupsDAO.delete(name)
    }

    override suspend fun createTranslate(groupName: String, words: List<String>, variants: List<String>) {
        val group = groupsDAO.get(groupName)
        val id = wordsDAO.insert().id
        words.forEachIndexed { index, word ->
            wordVariantsDAO.insert(WordVariant(id, word))
            variantsDAO.insert(TranslatedVariant(id, variants[index]))
        }
    }

    override suspend fun editTranslate(groupName: String, id: Long, words: List<String>, variants: List<String>) {
        wordVariantsDAO.delete(id)
        variantsDAO.delete(id)
        words.forEachIndexed { index, word ->
            wordVariantsDAO.insert(WordVariant(id, word))
            variantsDAO.insert(TranslatedVariant(id, variants[index]))
        }
    }

    override suspend fun deleteTranslate(groupName: String, id: Long) {
        wordsDAO.delete(wordsDAO.getAll().filter { it.id == id }.single())
    }

    private fun GroupEntity.toTranslatedGroup(): TranslatedGroup {
        return TranslatedGroup(
            name = name,
            translated = wordsDAO.getAll().map { word ->
                Translated(word.id,
                    wordVariantsDAO.getAll().map { it.variant },
                    variantsDAO.getAll().map { it.variant }
                )
            }
        )
    }

    @Database(entities = [
        GroupEntity::class,
        TranslatedVariant::class,
        TranslatedWord::class,
        WordVariant::class
    ],
        version = 1
    )
    internal abstract class AppDatabase : RoomDatabase() {
        abstract fun translatedGroups(): TranslatedGroupsDAO
        abstract fun translatedVariants(): TranslatedVariantsDAO
        abstract fun translatedWordsDao(): TranslatedWordsDAO
        abstract fun wordVariantsDao(): WordVariantsDAO
    }
}