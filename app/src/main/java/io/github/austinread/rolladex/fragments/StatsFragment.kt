package io.github.austinread.rolladex.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import io.github.austinread.rolladex.R
import io.github.austinread.rolladex.activities.CharacterSheetActivity
import io.github.austinread.rolladex.databinding.FragmentStatsBinding
import io.github.austinread.rolladex.viewmodels.CharacterSheetViewModel
import io.github.austinread.rolladex.viewmodels.CharacterSheetViewModelFactory

class StatsFragment : Fragment() {
    private lateinit var vm: CharacterSheetViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val characterId = arguments!!.getLong(CharacterSheetActivity.ARG_CHARACTER_ID)

        val binding = FragmentStatsBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val vmFactory = CharacterSheetViewModelFactory(activity!!.application, characterId)
        vm = ViewModelProvider(this, vmFactory).get(CharacterSheetViewModel::class.java)

        vm.character.observe(viewLifecycleOwner, Observer{ character -> binding.cs = character})

        return view
    }
}
