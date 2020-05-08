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

        val editCharacterName = findViewById<EditText>(R.id.et_characterName)
        val saveButton = findViewById<Button>(R.id.button_saveCharacter)

        saveButton.setOnClickListener{
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editCharacterName.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else{
                val name = editCharacterName.text.toString()

                replyIntent.putExtra(EXTRA_REPLY, name)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object{
        const val EXTRA_REPLY = "NEW_CHARACTER_REPLY"
    }
}
