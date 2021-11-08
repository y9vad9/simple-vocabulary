package me.y9vad9.vocabulary.storage.room

import androidx.room.*

@Dao
internal interface TranslatedVariantsDAO {
    @Insert
    fun insert(variant: TranslatedVariant)

    @Delete
    fun delete(variant: TranslatedVariant)

    @Query("DELETE FROM translatedvariant WHERE translatedId=:translatedId")
    fun delete(translatedId: Long)

    @Query("UPDATE translatedvariant SET translatedId = :newVariant WHERE translatedId=:id AND variant=:oldVariant")
    fun update(id: Long, oldVariant: String, newVariant: String)

    @Query("SELECT * FROM translatedvariant WHERE 1")
    fun getAll(): List<TranslatedVariant>
}

@Entity
internal data class TranslatedVariant(val translatedId: Long, val variant: String)