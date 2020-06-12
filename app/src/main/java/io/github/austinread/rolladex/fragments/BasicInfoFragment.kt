package io.github.austinread.rolladex.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.austinread.rolladex.R
import io.github.austinread.rolladex.activities.CharacterSheetActivity
import io.github.austinread.rolladex.activities.EditBasicInfoActivity

import io.github.austinread.rolladex.databinding.FragmentBasicInfoBinding
import io.github.austinread.rolladex.entities.CharacterSheet
import io.github.austinread.rolladex.viewmodels.CharacterSheetViewModel
import io.github.austinread.rolladex.viewmodels.CharacterSheetViewModelFactory

class BasicInfoFragment : Fragment() {
    private lateinit var vm: CharacterSheetViewModel

    private var editBasicInfoResultCode = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val characterId = arguments!!.getLong(CharacterSheetActivity.ARG_CHARACTER_ID)

        val binding = FragmentBasicInfoBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val vmFactory = CharacterSheetViewModelFactory(activity!!.application, characterId)
        vm = ViewModelProvider(this, vmFactory).get(CharacterSheetViewModel::class.java)

        vm.character.observe(viewLifecycleOwner, Observer{ character -> binding.cs = character})

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(activity, EditBasicInfoActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_EDIT_CHARACTER_BI, vm.character.value)

            intent.putExtra(EXTRA_EDIT_CHARACTER_BUNDLE_BI, bundle)
            startActivityForResult(intent, editBasicInfoResultCode)
        }

        return view
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

    companion object{
        const val EXTRA_EDIT_CHARACTER_BI = "EDIT_CHARACTER_BI"
        const val EXTRA_EDIT_CHARACTER_BUNDLE_BI = "EDIT_CHARACTER_BUNDLE_BI"
    }
}