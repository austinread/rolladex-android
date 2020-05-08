package io.github.austinread.rolladex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.austinread.rolladex.R
import io.github.austinread.rolladex.entities.CharacterSheet

class CharacterAdapter(private val characterClickListener: CharacterClickListener): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
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
        val item = characters[position]
        holder.name.text = item.Name

        holder.bind(item.id, characterClickListener)
    }

    interface CharacterClickListener{
        fun onCharacterClicked(id: Long?)
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.characterList_characterName)

        fun bind(id: Long?, listener: CharacterClickListener){
            itemView.setOnClickListener{listener.onCharacterClicked(id)}
        }
    }
}