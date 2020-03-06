package dev.marcosfarias.pokedex.data.repository.mapper

import dev.marcosfarias.pokedex.data.local.entity.PokemonEntity
import dev.marcosfarias.pokedex.data.model.Pokemon
import dev.marcosfarias.pokedex.data.remote.response.PokemonResponse

class PokemonMapper {

    fun toPokemonsFromPokemonsResponse(response: List<PokemonResponse>): List<Pokemon> {
        return response.map { it.toEntity() }
    }

    fun toPokemonsFromPokemonEntity(entitiy: List<PokemonEntity>): List<Pokemon> {
        return entitiy.map { it.toEntity() }
    }

    fun toPokemonEntitiesFromPokemons(items: List<Pokemon>): List<PokemonEntity> {
        return items.map { PokemonEntity.create(it) }
    }

    fun toPokemonFromPokemonEntity(entitiy: PokemonEntity): Pokemon {
        return entitiy.toEntity()
    }
}