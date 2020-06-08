package io.github.austinread.rolladex.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.austinread.rolladex.R
import io.github.austinread.rolladex.entities.CharacterSheet

class CharacterAdapter(private val characterClickListener: CharacterClickListener, private val context: Context): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    var characters = listOf<CharacterSheet>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_layout, parent, false)

        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    internal fun setCharacters(characters: List<CharacterSheet>){
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.name.text = character.Name
        holder.level.text = context.getString(R.string.level_display, character.Level.toString())
        holder.raceClass.text = context.getString(R.string.character_class_display, character.Race, character.CharacterClass)

        if(character.CharacterClass == context.getString(R.string.artificer)){
            holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_artificer))
        }
        if(character.CharacterClass == context.getString(R.string.barbarian)){
            holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_barbarian))
        }
        if(character.CharacterClass == context.getString(R.string.bard)){
            holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_bard))
        }
        if(character.CharacterClass == context.getString(R.string.cleric)){
            holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_cleric))
        }
        if(character.CharacterClass == context.getString(R.string.druid)){
            holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_druid))
        }
        if(character.CharacterClass == context.getString(R.string.fighter)){
            holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_fighter))
        }
        if(character.CharacterClass == context.getString(R.string.monk)){
            holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_monk))
        }
        if(character.CharacterClass == context.getString(R.string.paladin)){
            holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_paladin))
        }
        if(character.CharacterClass == context.getString(R.string.ranger)){
            holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_ranger))
        }
        if(character.CharacterClass == context.getString(R.string.rogue)){
            holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_rogue))
        }
        if(character.CharacterClass == context.getString(R.string.sorcerer)){
            holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_sorcerer))
        }
        if(character.CharacterClass == context.getString(R.string.warlock)){
            holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_warlock))
        }
        if(character.CharacterClass == context.getString(R.string.wizard)){
            holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_wizard))
        }

        holder.classIcon.contentDescription = character.CharacterClass

        holder.bind(character.id, characterClickListener)
    }

    interface CharacterClickListener{
        fun onCharacterClicked(id: Long?)
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.characterList_characterName)
        val level: TextView = itemView.findViewById(R.id.characterList_characterLevel)
        val raceClass: TextView = itemView.findViewById(R.id.characterList_characterRaceClass)
        val classIcon: ImageView = itemView.findViewById(R.id.class_icon)

        fun bind(id: Long?, listener: CharacterClickListener){
            itemView.setOnClickListener{listener.onCharacterClicked(id)}
        }
    }
}