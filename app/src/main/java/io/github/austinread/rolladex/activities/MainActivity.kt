package io.github.austinread.rolladex.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.austinread.rolladex.R
import io.github.austinread.rolladex.adapters.CharacterAdapter
import io.github.austinread.rolladex.entities.CharacterSheet
import io.github.austinread.rolladex.viewmodels.CharacterViewModel

class MainActivity : AppCompatActivity(), CharacterAdapter.CharacterClickListener {
    private var newCharacterIntentResultCode = 1
    private lateinit var characterVM: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView  = findViewById<RecyclerView>(R.id.rv_characters)
        val adapter = CharacterAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        characterVM = ViewModelProvider(this).get(CharacterViewModel::class.java)
        characterVM.characters.observe(this, Observer{ characters -> characters.let { adapter.setCharacters(it) }})

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(this@MainActivity, NewCharacterActivity::class.java)
            startActivityForResult(intent, newCharacterIntentResultCode)
        }
    }

    override fun onStart() {
        super.onStart()

        handleDelete()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newCharacterIntentResultCode && resultCode == Activity.RESULT_OK){
            data?.getBundleExtra(NewCharacterActivity.EXTRA_BUNDLE)?.let {
                characterVM.add(it.getParcelable<CharacterSheet>(NewCharacterActivity.EXTRA_CHARACTER) as CharacterSheet)
            }
        }
        //TODO: Error Handling
    }

    override fun onCharacterClicked(id: Long?) {
        val intent = Intent(this@MainActivity, CharacterSheetActivity::class.java)
        intent.putExtra(EXTRA_VIEW_CHARACTER_ID, id)
        startActivity(intent)
    }

    private fun handleDelete(){
        val deletedId = intent.getLongExtra(CharacterSheetActivity.EXTRA_DELETE_CHARACTER_ID, -1)
        if (deletedId != (-1).toLong()){
            characterVM.delete(deletedId)
        }
    }

    companion object{
        const val EXTRA_VIEW_CHARACTER_ID = "VIEW_CHARACTER_ID"
    }
}