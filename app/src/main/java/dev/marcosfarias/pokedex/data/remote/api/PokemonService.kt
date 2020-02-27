package dev.marcosfarias.pokedex.data.remote.api

import dev.marcosfarias.pokedex.data.remote.response.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon.json")
    suspend fun getPokemons(): Response<List<PokemonResponse>>
}
