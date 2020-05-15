package io.github.austinread.rolladex.activities

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        val characterId = intent.getLongExtra(MainActivity.EXTRA_VIEW_CHARACTER_ID, -1)

        vmFactory = CharacterSheetViewModelFactory(applicationContext as Application, characterId)
        vm = ViewModelProvider(this, vmFactory).get(CharacterSheetViewModel::class.java)

        vm.character.observe(this, Observer{ character -> bindCharacterData(character) })
    }

    private fun bindCharacterData(character: CharacterSheet){
        //TODO: Data Binding
        val nameTV = findViewById<TextView>(R.id.tv_character_name)
        val levelTV = findViewById<TextView>(R.id.tv_character_level)
        nameTV.text = character.Name
        levelTV.text = character.Level.toString()
    }

    ///region Action Menu

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.charactersheet_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.delete_character -> {
                vm.character.removeObservers(this)

                val intent = Intent(this@CharacterSheetActivity, MainActivity::class.java)
                intent.putExtra(EXTRA_DELETE_CHARACTER_ID, vm.character.value?.id)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    ///endregion

    companion object{
        const val EXTRA_DELETE_CHARACTER_ID = "DELETE_CHARACTER_ID"
    }
}