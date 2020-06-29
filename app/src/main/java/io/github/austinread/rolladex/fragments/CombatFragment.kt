package io.github.austinread.rolladex.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import io.github.austinread.rolladex.R
import io.github.austinread.rolladex.activities.CharacterSheetActivity
import io.github.austinread.rolladex.databinding.FragmentCombatBinding
import io.github.austinread.rolladex.viewmodels.CharacterSheetViewModel
import io.github.austinread.rolladex.viewmodels.CharacterSheetViewModelFactory

class CombatFragment : Fragment() {
    private lateinit var vm: CharacterSheetViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val characterId = arguments!!.getLong(CharacterSheetActivity.ARG_CHARACTER_ID)

        val binding = FragmentCombatBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val vmFactory = CharacterSheetViewModelFactory(activity!!.application, characterId)
        vm = ViewModelProvider(this, vmFactory).get(CharacterSheetViewModel::class.java)

        vm.character.observe(viewLifecycleOwner, Observer{ character -> binding.cs = character})

        setupListeners(view)

        return view
    }

    private fun setupListeners(view: View) {
        val editTextsToSave: IntArray = intArrayOf(
            R.id.et_character_armor,
            R.id.et_character_speed
        )

        editTextsToSave.forEach {
            view.findViewById<EditText>(it).addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (vm.character.value != null){
                        saveUpdates()
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }

    private fun saveUpdates(){
        vm.update(vm.character.value!!)
    }
}
