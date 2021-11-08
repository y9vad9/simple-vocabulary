package me.y9vad9.vocabulary.storage.room

import androidx.room.*

@Dao
internal interface TranslatedWordsDAO {
    @Insert
    fun insert(): TranslatedWord

    @Delete
    fun delete(translatedWord: TranslatedWord)

    @Query("SELECT * FROM translatedword WHERE 1")
    fun getAll(): List<TranslatedWord>

    @Query("SELECT * FROM translatedword WHERE id=:id")
    fun get(id: Long): TranslatedWord
}

@Entity
internal class TranslatedWord(
    @PrimaryKey val id: Long
)