package dev.marcosfarias.pokedex.ui.home

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.data.model.MenuType
import dev.marcosfarias.pokedex.data.model.Menu
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.item_pokemon.view.*

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    private val menus = mutableListOf<Menu>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    override fun getItemCount(): Int {
        return menus.size
    }

    private fun getItem(position: Int) = menus[position]

    fun addAll(items: List<Menu>) {
        menus.clear()
        menus.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val context = itemView.context

        init {
            itemView.setOnClickListener {
                it.findNavController().navigate(R.id.action_navigation_home_to_navigation_pokedex)
            }
        }

        fun bindView(item: Menu) {
            val nameAndColor = when (item.type) {
                MenuType.POKEDEX -> Pair(context.getString(R.string.menu_item_1), R.color.lightTeal)
                MenuType.MOVES -> Pair(context.getString(R.string.menu_item_2), R.color.lightRed)
                MenuType.ABILITIES -> Pair(context.getString(R.string.menu_item_3), R.color.lightBlue)
                MenuType.ITEMS -> Pair(context.getString(R.string.menu_item_4), R.color.lightYellow)
                MenuType.LOCATIONS -> Pair(context.getString(R.string.menu_item_5), R.color.lightPurple)
                MenuType.TYPE_CHARTS -> Pair(context.getString(R.string.menu_item_6), R.color.lightBrown)
            }

            itemView.textViewName.text = nameAndColor.first

            val color = PokemonColorUtil(context).convertColor(nameAndColor.second)
            itemView.relativeLayoutBackground.background.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }
}
