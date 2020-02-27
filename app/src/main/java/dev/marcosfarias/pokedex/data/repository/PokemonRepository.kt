package dev.marcosfarias.pokedex.data.repository

import dev.marcosfarias.pokedex.data.local.datasource.PokemonLocalDataSource
import dev.marcosfarias.pokedex.data.model.Pokemon
import dev.marcosfarias.pokedex.data.remote.datasource.PokemonRemoteDataSource
import dev.marcosfarias.pokedex.data.repository.mapper.PokemonMapper
import dev.marcosfarias.pokedex.data.safeApiCall
import dev.marcosfarias.pokedex.data.model.Result

class PokemonRepository(
    private val localDataSource: PokemonLocalDataSource,
    private val remoteDateSource: PokemonRemoteDataSource,
    private val mapper: PokemonMapper
) {

    suspend fun getPockmons(): Result<List<Pokemon>> {
        val pokemons = localDataSource.getPokemons()
        if (!pokemons.isNullOrEmpty()) {
            return Result.Success(mapper.toPokemonsFromPokemonEntity(pokemons))
        }

        return safeApiCall(call = {
            remoteDateSource.getPokemons()
        }, transform = {
            val items = mapper.toPokemonsFromPokemonsResponse(it)
            localDataSource.addAll(mapper.toPokemonEntitiesFromPokemons(items))
            items
        })
    }
}