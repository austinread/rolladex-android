package io.github.austinread.rolladex.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.github.austinread.rolladex.R

import io.github.austinread.rolladex.activities.CharacterSheetActivity
import io.github.austinread.rolladex.databinding.FragmentStatsBinding
import io.github.austinread.rolladex.dialogs.AbilityScoreDialog
import io.github.austinread.rolladex.viewmodels.CharacterSheetViewModel
import io.github.austinread.rolladex.viewmodels.CharacterSheetViewModelFactory

class StatsFragment : Fragment(), AbilityScoreDialog.AbilityScoreDialogListener {
    private lateinit var vm: CharacterSheetViewModel
    private lateinit var binding: FragmentStatsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val characterId = arguments!!.getLong(CharacterSheetActivity.ARG_CHARACTER_ID)

        binding = FragmentStatsBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val vmFactory = CharacterSheetViewModelFactory(activity!!.application, characterId)
        vm = ViewModelProvider(this, vmFactory).get(CharacterSheetViewModel::class.java)

        vm.character.observe(viewLifecycleOwner, Observer{ character -> binding.cs = character})

        setupListeners(view)

        return view
    }

    ///region Ability Score Dialog

    private fun showAbilityScoreDialog(name: String, score: Int){
        val dialogFragment = AbilityScoreDialog()
        dialogFragment.arguments = Bundle().apply{
            putString(ARG_ABILITY_NAME, name)
            putInt(ARG_ABILITY_VALUE, score)
        }
        dialogFragment.setTargetFragment(this, 0)
        dialogFragment.show(parentFragmentManager, "ability_score")
    }

    override fun onAbilityScoreUpdateClick(abilityName: String, newScore: Int) {
        when(abilityName){
            getString(R.string.strength) -> binding.cs!!.Strength = newScore
            getString(R.string.dexterity) -> binding.cs!!.Dexterity = newScore
            getString(R.string.constitution) -> binding.cs!!.Constitution = newScore
            getString(R.string.intelligence) -> binding.cs!!.Intelligence = newScore
            getString(R.string.wisdom) -> binding.cs!!.Wisdom = newScore
            getString(R.string.charisma) -> binding.cs!!.Charisma = newScore
        }

        saveUpdates()
    }

    ///endregion

    private fun setupListeners(view: View){
        view.findViewById<EditText>(R.id.et_character_proficiency).addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (vm.character.value != null){
                    saveUpdates()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        val checkboxesToSave: IntArray = intArrayOf(
            R.id.cb_character_inspiration,
            R.id.cb_saving_str,
            R.id.cb_saving_dex,
            R.id.cb_saving_con,
            R.id.cb_saving_int,
            R.id.cb_saving_wis,
            R.id.cb_saving_char,
            R.id.cb_acrobatics,
            R.id.cb_animalHandling,
            R.id.cb_arcana,
            R.id.cb_athletics,
            R.id.cb_deception,
            R.id.cb_history,
            R.id.cb_insight,
            R.id.cb_intimidation,
            R.id.cb_investigation,
            R.id.cb_medicine,
            R.id.cb_nature,
            R.id.cb_perception,
            R.id.cb_performance,
            R.id.cb_persuasion,
            R.id.cb_religion,
            R.id.cb_slightOfHand,
            R.id.cb_stealth,
            R.id.cb_survival
        )

        checkboxesToSave.forEach {
            view.findViewById<CheckBox>(it).setOnClickListener(){
                saveUpdates()
            }
        }

        view.findViewById<LinearLayout>(R.id.container_strength).setOnClickListener {
            showAbilityScoreDialog(getString(R.string.strength), binding.cs!!.Strength)
        }
        view.findViewById<LinearLayout>(R.id.container_dex).setOnClickListener {
            showAbilityScoreDialog(getString(R.string.dexterity), binding.cs!!.Dexterity)
        }
        view.findViewById<LinearLayout>(R.id.container_con).setOnClickListener {
            showAbilityScoreDialog(getString(R.string.constitution), binding.cs!!.Constitution)
        }
        view.findViewById<LinearLayout>(R.id.container_int).setOnClickListener {
            showAbilityScoreDialog(getString(R.string.intelligence), binding.cs!!.Intelligence)
        }
        view.findViewById<LinearLayout>(R.id.container_wis).setOnClickListener {
            showAbilityScoreDialog(getString(R.string.wisdom), binding.cs!!.Wisdom)
        }
        view.findViewById<LinearLayout>(R.id.container_char).setOnClickListener {
            showAbilityScoreDialog(getString(R.string.charisma), binding.cs!!.Charisma)
        }
    }

    private fun saveUpdates(){
        vm.update(vm.character.value!!) //lol nullchecking is for nerds
    }

    companion object{
        const val ARG_ABILITY_NAME = "ABILITY_NAME"
        const val ARG_ABILITY_VALUE = "ABILITY_VALUE"
    }

}