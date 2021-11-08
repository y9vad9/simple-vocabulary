package me.y9vad9.vocabulary.storage.room

import androidx.room.*

@Dao
internal interface TranslatedGroupsDAO {
    @Query("DELETE FROM groupentity WHERE name=':groupName'")
    fun delete(groupName: String)

    @Insert
    fun insert(entity: GroupEntity)

    @Delete
    fun delete(entity: GroupEntity)

    @Query("SELECT * FROM groupentity WHERE name=:name")
    fun get(name: String): GroupEntity

    @Query("SELECT * FROM groupentity WHERE 1")
    fun getAll(): List<GroupEntity>
}

@Entity
internal data class GroupEntity(val name: String)