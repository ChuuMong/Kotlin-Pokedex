package dev.marcosfarias.pokedex.data.local.datasource

import dev.marcosfarias.pokedex.data.local.dao.PokemonDAO
import dev.marcosfarias.pokedex.data.local.entity.PokemonEntity

class PokemonLocalDataSource(private val pokemonDao: PokemonDAO) {
    fun getPokemons() = pokemonDao.all()

    fun addAll(entities: List<PokemonEntity>) = pokemonDao.addAll(entities)

    fun getPokemonById(id: String) = pokemonDao.getById(id)
}