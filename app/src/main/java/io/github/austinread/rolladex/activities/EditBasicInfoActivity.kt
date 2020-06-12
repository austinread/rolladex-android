package io.github.austinread.rolladex.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import io.github.austinread.rolladex.R
import io.github.austinread.rolladex.entities.CharacterSheet
import io.github.austinread.rolladex.fragments.BasicInfoFragment

class EditBasicInfoActivity : AppCompatActivity() {

    private lateinit var cs: CharacterSheet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_basic_info)

        val races = resources.getStringArray(R.array.races_array)
        val raceArrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, races)
        val classes = resources.getStringArray(R.array.classes_array)
        val classArrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, classes)

        val editCharacterName = findViewById<EditText>(R.id.cs_et_characterName)
        val editCharacterLevel = findViewById<EditText>(R.id.cs_et_characterLevel)

        val autoCompleteCharacterRace = findViewById<AutoCompleteTextView>(R.id.cs_actv_characterRace)
        autoCompleteCharacterRace.threshold = 1
        autoCompleteCharacterRace.setAdapter(raceArrayAdapter)

        val autoCompleteCharacterClass = findViewById<AutoCompleteTextView>(R.id.cs_actv_characterClass)
        autoCompleteCharacterClass.threshold = 1
        autoCompleteCharacterClass.setAdapter(classArrayAdapter)

        intent.getBundleExtra(BasicInfoFragment.EXTRA_EDIT_CHARACTER_BUNDLE_BI)?.let {
            cs = it.getParcelable<CharacterSheet>(BasicInfoFragment.EXTRA_EDIT_CHARACTER_BI) as CharacterSheet

            editCharacterName.setText(cs.Name)
            editCharacterLevel.setText(cs.Level.toString())
            autoCompleteCharacterRace.setText(cs.Race)
            autoCompleteCharacterClass.setText(cs.CharacterClass)
        }

        val saveButton = findViewById<Button>(R.id.cs_button_saveBasicInfo)

        saveButton.setOnClickListener{
            cs.Name = editCharacterName.text.toString()
            cs.Level = editCharacterLevel.text.toString().toInt()
            cs.Race = autoCompleteCharacterRace.text.toString()
            cs.CharacterClass = autoCompleteCharacterClass.text.toString()

            val replyIntent = Intent()

            val bundle = Bundle()
            bundle.putParcelable(EXTRA_CHARACTER, cs)

            replyIntent.putExtra(EXTRA_CHARACTER_BUNDLE, bundle)

            setResult(Activity.RESULT_OK, replyIntent)

            finish()
        }
    }

    companion object{
        const val EXTRA_CHARACTER = "CHARACTER"
        const val EXTRA_CHARACTER_BUNDLE = "CHARACTER_BUNDLE"
    }
}
