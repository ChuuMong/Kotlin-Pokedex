package dev.marcosfarias.pokedex.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.marcosfarias.pokedex.data.model.Menu
import dev.marcosfarias.pokedex.data.model.News
import dev.marcosfarias.pokedex.data.model.Pokemon
import dev.marcosfarias.pokedex.ui.home.MenuAdapter
import dev.marcosfarias.pokedex.ui.home.NewsAdapter
import dev.marcosfarias.pokedex.ui.pokedex.PokemonAdapter


@BindingAdapter("homeMenus")
fun setHomeMenusAdapter(rv: RecyclerView, items: List<Menu>?) {
    items ?: return

    rv.layoutManager = GridLayoutManager(rv.context, 2)
    rv.adapter = MenuAdapter().apply {
        addAll(items)
    }
}

@BindingAdapter("homeNews")
fun setHomeNewsAdapter(rv: RecyclerView, items: List<News>?) {
    items ?: return

    rv.layoutManager = GridLayoutManager(rv.context, 1)
    rv.addItemDecoration(DividerItemDecoration(rv.context, DividerItemDecoration.VERTICAL))
    rv.adapter = NewsAdapter().apply {
        addAll(items)
    }
}

@BindingAdapter("pokemons")
fun setPokemonAdapter(rv: RecyclerView, items: List<Pokemon>?) {
    items ?: return

    rv.layoutManager = GridLayoutManager(rv.context, 2)
    rv.adapter = PokemonAdapter().apply {
        addAll(items)
    }
}