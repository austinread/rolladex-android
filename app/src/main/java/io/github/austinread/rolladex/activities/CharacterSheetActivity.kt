package io.github.austinread.rolladex.activities

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.austinread.rolladex.R
import io.github.austinread.rolladex.databinding.ActivityCharacterSheetBinding
import io.github.austinread.rolladex.entities.CharacterSheet
import io.github.austinread.rolladex.viewmodels.CharacterSheetViewModel
import io.github.austinread.rolladex.viewmodels.CharacterSheetViewModelFactory

class CharacterSheetActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCharacterSheetBinding
    private lateinit var vmFactory: CharacterSheetViewModelFactory
    private lateinit var vm: CharacterSheetViewModel

    private var editBasicInfoResultCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_sheet)

        val characterId = intent.getLongExtra(MainActivity.EXTRA_VIEW_CHARACTER_ID, -1)

        vmFactory = CharacterSheetViewModelFactory(applicationContext as Application, characterId)
        vm = ViewModelProvider(this, vmFactory).get(CharacterSheetViewModel::class.java)

        vm.character.observe(this, Observer{ character -> binding.cs = character})

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(this@CharacterSheetActivity, EditBasicInfoActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_EDIT_CHARACTER_BI, vm.character.value)

            intent.putExtra(EXTRA_EDIT_CHARACTER_BUNDLE_BI, bundle)
            startActivityForResult(intent, editBasicInfoResultCode)
        }
    }

    //Handle Edit from Basic Info Edit Activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == editBasicInfoResultCode && resultCode == Activity.RESULT_OK) {
            data?.getBundleExtra(EditBasicInfoActivity.EXTRA_CHARACTER_BUNDLE)?.let {
                vm.update(it.getParcelable<CharacterSheet>(EditBasicInfoActivity.EXTRA_CHARACTER) as CharacterSheet)
            }
        }
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
        const val EXTRA_EDIT_CHARACTER_BI = "EDIT_CHARACTER_BI"
        const val EXTRA_EDIT_CHARACTER_BUNDLE_BI = "EDIT_CHARACTER_BUNDLE_BI"
    }
}