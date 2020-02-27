package dev.marcosfarias.pokedex.data.remote.datasource

import dev.marcosfarias.pokedex.data.remote.api.PokemonService

class PokemonRemoteDataSource(private val service: PokemonService) {

    suspend fun getPokemons() = service.getPokemons()
}