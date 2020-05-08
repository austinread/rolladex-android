package io.github.austinread.rolladex.activities

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.github.austinread.rolladex.R
import io.github.austinread.rolladex.entities.CharacterSheet
import io.github.austinread.rolladex.viewmodels.CharacterSheetViewModel
import io.github.austinread.rolladex.viewmodels.CharacterSheetViewModelFactory

class CharacterSheetActivity : AppCompatActivity() {
    private lateinit var vmFactory: CharacterSheetViewModelFactory
    private lateinit var vm: CharacterSheetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_sheet)

        val characterId = intent.getLongExtra(MainActivity.EXTRA_CHARACTER_ID, -1)

        vmFactory = CharacterSheetViewModelFactory(applicationContext as Application, characterId)
        vm = ViewModelProvider(this, vmFactory).get(CharacterSheetViewModel::class.java)

        vm.character.observe(this, Observer{ character -> bindCharacterData(character) })
    }

    private fun bindCharacterData(character: CharacterSheet){
        //TODO: Data Binding
        val nameTV = findViewById<TextView>(R.id.tv_character_name)
        nameTV.text = character.Name
    }
}