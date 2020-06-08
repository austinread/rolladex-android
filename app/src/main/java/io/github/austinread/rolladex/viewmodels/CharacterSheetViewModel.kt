package io.github.austinread.rolladex.viewmodels

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import io.github.austinread.rolladex.entities.CharacterSheet
import io.github.austinread.rolladex.repositories.CharacterRepository
import io.github.austinread.rolladex.utils.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CharacterSheetViewModel(app: Application, id: Long): AndroidViewModel(app) {
    private val repo: CharacterRepository = CharacterRepository(AppDatabase.getInstance(app).characterDao())

    val character: LiveData<CharacterSheet>

    init{
        character = repo.getById(id)
    }

    fun update(cs: CharacterSheet) = viewModelScope.launch(Dispatchers.IO) {
        repo.update(cs)
    }
}

@Suppress("UNCHECKED_CAST")
class CharacterSheetViewModelFactory(private val app: Application, private val id:  Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterSheetViewModel::class.java)){
            return CharacterSheetViewModel(app, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}