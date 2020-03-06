package dev.marcosfarias.pokedex

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.marcosfarias.pokedex.data.local.entity.PokemonEntity
import dev.marcosfarias.pokedex.data.model.TestPokemon
import dev.marcosfarias.pokedex.data.remote.response.PokemonResponse
import dev.marcosfarias.pokedex.utils.empty
import java.io.File


fun Any.getJson(path: String): String {
    val uri = this::class.java.classLoader?.getResource(path) ?: return String.empty()
    return File(uri.path).readText(Charsets.UTF_8)
}


fun Any.getPokemonFromJson(): List<TestPokemon> {
    val json = getJson("json/pokemon/pokemons.json")
    return Gson().fromJson(json, object : TypeToken<List<TestPokemon>>() {}.type)
}

fun toPokemonEntity() = object : (TestPokemon) -> PokemonEntity {
    override fun invoke(test: TestPokemon): PokemonEntity {
        return test.toPokemonEntity()
    }
}

fun toPokemonResponse() = object : (TestPokemon) -> PokemonResponse {
    override fun invoke(test: TestPokemon): PokemonResponse {
        return test.toPokemonResponse()
    }
}