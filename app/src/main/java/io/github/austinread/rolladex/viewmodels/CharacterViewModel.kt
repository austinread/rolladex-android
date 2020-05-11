package io.github.austinread.rolladex.viewmodels

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import io.github.austinread.rolladex.entities.CharacterSheet
import io.github.austinread.rolladex.repositories.CharacterRepository
import io.github.austinread.rolladex.utils.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel(app: Application): AndroidViewModel(app) {
    private val repo: CharacterRepository = CharacterRepository(AppDatabase.getInstance(app).characterDao())

    val characters: LiveData<List<CharacterSheet>>

    init{
        characters = repo.allCharacters
    }

    fun add(character: CharacterSheet) = viewModelScope.launch(Dispatchers.IO) {
        repo.add(character)
    }

    fun delete(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(id)
    }
}