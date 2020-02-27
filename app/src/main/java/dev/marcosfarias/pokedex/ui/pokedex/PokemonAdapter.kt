package dev.marcosfarias.pokedex.ui.pokedex

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.data.local.entity.PokemonEntity
import dev.marcosfarias.pokedex.data.model.Pokemon
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private val pokemons = mutableListOf<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    private fun getItem(position: Int) = pokemons[position]

    fun addAll(items: List<Pokemon>) {
        pokemons.clear()
        pokemons.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                var bundle = bundleOf("id" to getItem(adapterPosition).id)
                it.findNavController()
                    .navigate(R.id.action_navigation_pokedex_to_navigation_dashboard, bundle)
            }
        }

        fun bindView(item: Pokemon) {
            itemView.textViewName.text = item.name
            itemView.textViewID.text = item.id

            val color = PokemonColorUtil(itemView.context).getPokemonColor(item.typeOfPokemon)
            itemView.relativeLayoutBackground.background.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

            item.typeOfPokemon.getOrNull(0).let { firstType ->
                itemView.textViewType3.text = firstType
                itemView.textViewType3.isVisible = firstType != null
            }

            item.typeOfPokemon.getOrNull(1).let { secondType ->
                itemView.textViewType2.text = secondType
                itemView.textViewType2.isVisible = secondType != null
            }

            item.typeOfPokemon.getOrNull(2).let { thirdType ->
                itemView.textViewType1.text = thirdType
                itemView.textViewType1.isVisible = thirdType != null
            }

            Glide.with(itemView.context)
                .load(item.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(android.R.color.transparent)
                .into(itemView.imageView)
        }
    }
}
