package io.github.austinread.rolladex.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import io.github.austinread.rolladex.entities.CharacterSheet

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getAll(): LiveData<List<CharacterSheet>>

    @Query("SELECT * FROM character WHERE id = :id")
    fun getById(id: Long): LiveData<CharacterSheet>

    @Insert
    suspend fun add(characterSheet: CharacterSheet)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(characterSheet: CharacterSheet)

    @Delete
    suspend fun delete(characterSheet: CharacterSheet)
}