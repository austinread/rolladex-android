package io.github.austinread.rolladex.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import io.github.austinread.rolladex.R
import io.github.austinread.rolladex.entities.CharacterSheet

class NewCharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)

        val races = resources.getStringArray(R.array.races_array)
        val raceArrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, races)
        val classes = resources.getStringArray(R.array.classes_array)
        val classArrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, classes)

        val editCharacterName = findViewById<EditText>(R.id.et_characterName)
        val editCharacterLevel = findViewById<EditText>(R.id.et_characterLevel)

        val autoCompleteCharacterRace = findViewById<AutoCompleteTextView>(R.id.actv_characterRace)
        autoCompleteCharacterRace.threshold = 1
        autoCompleteCharacterRace.setAdapter(raceArrayAdapter)

        val autoCompleteCharacterClass = findViewById<AutoCompleteTextView>(R.id.actv_characterClass)
        autoCompleteCharacterClass.threshold = 1
        autoCompleteCharacterClass.setAdapter(classArrayAdapter)

        val saveButton = findViewById<Button>(R.id.button_saveCharacter)

        saveButton.setOnClickListener{
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editCharacterName.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else{
                val name = editCharacterName.text.toString()
                val level = editCharacterLevel.text.toString().toInt()
                val race = autoCompleteCharacterRace.text.toString()
                val characterClass = autoCompleteCharacterClass.text.toString()

                val newCharacter = CharacterSheet(null, name, level, race, characterClass)
                val bundle = Bundle()
                bundle.putParcelable(EXTRA_CHARACTER, newCharacter)

                replyIntent.putExtra(EXTRA_BUNDLE, bundle)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object{
        const val EXTRA_BUNDLE = "NEW_CHARACTER_BUNDLE_REPLY"
        const val EXTRA_CHARACTER = "NEW_CHARACTER_REPLY"
    }
}
