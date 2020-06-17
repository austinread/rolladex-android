package io.github.austinread.rolladex.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment

import io.github.austinread.rolladex.R
import io.github.austinread.rolladex.fragments.StatsFragment
import io.github.austinread.rolladex.utils.BindingUtils

class AbilityScoreDialog : DialogFragment() {
    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val targetFragment = targetFragment!! as AbilityScoreDialogListener

            val abilityName = arguments!!.getString(StatsFragment.ARG_ABILITY_NAME)!!
            val abilityScore = arguments!!.getInt(StatsFragment.ARG_ABILITY_VALUE)

            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_ability_score, null)

            val modifierText = view.findViewById<TextView>(R.id.tv_ability_dialog_modifier)
            val np = view.findViewById<NumberPicker>(R.id.np_ability_score)
            np.minValue = 1
            np.maxValue = 30
            np.value = abilityScore
            modifierText.text = BindingUtils.getAbilityModifierString(abilityScore)
            np.setOnValueChangedListener{ _, _, newVal ->
                modifierText.text = BindingUtils.getAbilityModifierString(newVal)
            }

            builder
                .setTitle(abilityName)
                .setView(view)
                .setPositiveButton(getString(R.string.update)) { _, _ ->
                    targetFragment.onAbilityScoreUpdateClick(abilityName, np.value)
                    this.dismiss()
                }
                .setNegativeButton(getString(R.string.cancel)) { _, _ ->
                    dialog!!.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    interface AbilityScoreDialogListener{
        fun onAbilityScoreUpdateClick(abilityName: String, newScore: Int)
    }
}
