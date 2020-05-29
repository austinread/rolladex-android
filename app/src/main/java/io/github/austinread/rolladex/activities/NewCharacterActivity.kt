package io.github.austinread.rolladex.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import io.github.austinread.rolladex.R
import io.github.austinread.rolladex.entities.CharacterSheet

class NewCharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)

        //TODO: View/Data Binding?
        val editCharacterName = findViewById<EditText>(R.id.et_characterName)
        val editCharacterLevel = findViewById<EditText>(R.id.et_characterLevel)
        val editCharacterRace = findViewById<EditText>(R.id.et_characterRace)
        val editCharacterClass = findViewById<EditText>(R.id.et_characterClass)
        val saveButton = findViewById<Button>(R.id.button_saveCharacter)

        saveButton.setOnClickListener{
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editCharacterName.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else{
                val name = editCharacterName.text.toString()
                val level = editCharacterLevel.text.toString().toInt()
                val race = editCharacterRace.text.toString()
                val characterClass = editCharacterClass.text.toString()

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
