package io.github.austinread.rolladex.activities

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.github.austinread.rolladex.R
import io.github.austinread.rolladex.fragments.BasicInfoFragment
import io.github.austinread.rolladex.fragments.CombatFragment
import io.github.austinread.rolladex.fragments.StatsFragment
import io.github.austinread.rolladex.viewmodels.CharacterSheetViewModel
import io.github.austinread.rolladex.viewmodels.CharacterSheetViewModelFactory

private const val NUM_CS_TABS = 3

class CharacterSheetActivity : AppCompatActivity() {
    private lateinit var vmFactory: CharacterSheetViewModelFactory
    private lateinit var vm: CharacterSheetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_sheet)

        characterId = intent.getLongExtra(MainActivity.EXTRA_VIEW_CHARACTER_ID, -1)

        vmFactory = CharacterSheetViewModelFactory(applicationContext as Application, characterId)
        vm = ViewModelProvider(this, vmFactory).get(CharacterSheetViewModel::class.java)

        val viewPager : ViewPager2 = findViewById(R.id.pager_cs)
        val tabLayout : TabLayout = findViewById(R.id.tab_layout_cs)

        viewPager.adapter = CharacterSheetFragmentAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(position){
                0 -> "Basics"
                1 -> "Stats"
                2 -> "Combat"
                else -> ""
            }
            viewPager.setCurrentItem(tab.position, true)
        }.attach()
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
                intent.putExtra(EXTRA_DELETE_CHARACTER_ID, characterId)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    ///endregion

    companion object{
        const val EXTRA_DELETE_CHARACTER_ID = "DELETE_CHARACTER_ID"
        const val ARG_CHARACTER_ID = "ARG_CHARACTER_ID"

        var characterId: Long = 0   //This value should never be passed anywhere, this should be set before the CharacterSheetFragmentAdapter is created
    }
}

class CharacterSheetFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity){
    override fun getItemCount(): Int { return NUM_CS_TABS }

    override fun createFragment(position: Int): Fragment {
        val fragment = when(position){
            0 -> BasicInfoFragment()
            1 -> StatsFragment()
            2 -> CombatFragment()
            else -> BasicInfoFragment() //Should probably be an error or something
        }

        fragment.arguments = Bundle().apply{ putLong(CharacterSheetActivity.ARG_CHARACTER_ID, CharacterSheetActivity.characterId) }
        return fragment
    }
}
