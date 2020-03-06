package dev.marcosfarias.pokedex.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marcosfarias.pokedex.data.local.AppDatabase
import dev.marcosfarias.pokedex.data.local.entity.PokemonEntity
import dev.marcosfarias.pokedex.data.model.TestPokemon
import dev.marcosfarias.pokedex.data.repository.mapper.PokemonMapper
import dev.marcosfarias.pokedex.getPokemonFromJson
import dev.marcosfarias.pokedex.toPokemonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers.empty
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
class PokemonDAOTest {

    private val mapper = PokemonMapper()

    private lateinit var appDatabase: AppDatabase
    private lateinit var pokemonDao: PokemonDAO

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        pokemonDao = appDatabase.pokemonDAO()
    }

    @Test
    fun getPokemons_returnAllPokemons() = runBlocking(Dispatchers.IO) {
        pokemonDao.addAll(getPokemonFromJson().map(toPokemonEntity()))
        val pokemons = pokemonDao.all()

        assertThat(pokemons[0].name, `is`("Bulbasaur"))
        assertThat(pokemons[1].name, `is`("Ivysaur"))
        assertThat(pokemons[2].name, `is`("Venusaur"))
    }

    @Test
    fun getById_returnPokemonFromById() = runBlocking(Dispatchers.IO) {
        pokemonDao.addAll(getPokemonFromJson().map(toPokemonEntity()))
        val pokemon = pokemonDao.getById("#002")

        assertThat(pokemon.name, `is`("Ivysaur"))
    }

    @Test
    fun setAllPokemons_and_deleteAllPokemons_returnEmptyPokemons() = runBlocking(Dispatchers.IO) {
        pokemonDao.addAll(getPokemonFromJson().map(toPokemonEntity()))
        pokemonDao.deleteAll()

        val pokemons = pokemonDao.all()
        assertThat(pokemons, `is`(empty()))
    }

    @Test
    fun deletePokemon_and_getAllPokemons_returnNotIncludeDeletePokemon() =
        runBlocking(Dispatchers.IO) {
            val pokemonEntities = getPokemonFromJson().map(toPokemonEntity())
            pokemonDao.addAll(pokemonEntities)
            pokemonDao.delete(pokemonEntities[0])

            val pokemons = pokemonDao.all()
            assertThat(pokemons[0].name, `is`(not("Bulbasaur")))
        }

    @After
    fun endTest() {
        appDatabase.close()
        stopKoin()
    }
}
