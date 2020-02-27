package dev.marcosfarias.pokedex.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.marcosfarias.pokedex.data.local.dao.PokemonDAO
import dev.marcosfarias.pokedex.data.local.entity.PokemonEntity

class DashboardViewModel(private val pokemonDAO: PokemonDAO) : ViewModel() {

    fun getPokemonById(id: String?): LiveData<PokemonEntity> {
        return pokemonDAO.getById(id)
    }
}
