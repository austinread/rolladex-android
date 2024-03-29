package io.github.austinread.rolladex.repositories

import androidx.lifecycle.LiveData
import io.github.austinread.rolladex.daos.CharacterDao
import io.github.austinread.rolladex.entities.CharacterSheet

class CharacterRepository(private val characterDao: CharacterDao) {
    val allCharacters: LiveData<List<CharacterSheet>> = characterDao.getAll()

    suspend fun add(character: CharacterSheet){
        characterDao.add(character)
    }

    fun getById(id: Long): LiveData<CharacterSheet> {
        return characterDao.getById(id)
    }

    suspend fun update(character: CharacterSheet){
        characterDao.update(character)
    }

    suspend fun delete(id: Long) {
        characterDao.delete(id)
    }
}