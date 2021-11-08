package me.y9vad9.vocabulary.storage.room

import androidx.room.*

@Dao
internal interface WordVariantsDAO {
    @Insert
    fun insert(variant: WordVariant)

    @Query("DELETE FROM wordvariant WHERE translatedId=:translatedId")
    fun delete(translatedId: Long)

    @Delete
    fun delete(variant: WordVariant)

    @Query("UPDATE wordvariant SET translatedId = :newVariant WHERE translatedId=:id AND variant=:oldVariant")
    fun update(id: Long, oldVariant: String, newVariant: String)

    @Query("SELECT * FROM wordvariant WHERE 1")
    fun getAll(): List<WordVariant>
}

@Entity
internal data class WordVariant(val translatedId: Long, val variant: String)