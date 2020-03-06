package dev.marcosfarias.pokedex.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marcosfarias.pokedex.data.local.AppDatabase
import dev.marcosfarias.pokedex.data.local.entity.PokemonEntity
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
        pokemonDao.addAll(getTestPokemonEntities())
        val pokemons = pokemonDao.all()

        assertThat(pokemons[0].name, `is`("Bulbasaur"))
        assertThat(pokemons[1].name, `is`("Ivysaur"))
        assertThat(pokemons[2].name, `is`("Venusaur"))
    }

    @Test
    fun getById_returnPokemonFromById() = runBlocking(Dispatchers.IO) {
        pokemonDao.addAll(getTestPokemonEntities())
        val pokemon = pokemonDao.getById("#002")

        assertThat(pokemon.name, `is`("Ivysaur"))
    }

    @Test
    fun setAllPokemons_and_deleteAllPokemons_returnEmptyPokemons() = runBlocking(Dispatchers.IO) {
        pokemonDao.addAll(getTestPokemonEntities())
        pokemonDao.deleteAll()

        val pokemons = pokemonDao.all()
        assertThat(pokemons, `is`(empty()))
    }

    @Test
    fun deletePokemon_and_getAllPokemons_returnNotIncludeDeletePokemon() = runBlocking(Dispatchers.IO) {
        val pokemonEntities = getTestPokemonEntities()
        pokemonDao.addAll(pokemonEntities)
        pokemonDao.delete(pokemonEntities[0])

        val pokemons = pokemonDao.all()
        assertThat(pokemons[0].name, `is`(not("Bulbasaur")))
    }

    private fun getTestPokemonEntities() = listOf(
        PokemonEntity(
            "#001",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "Bulbasaur",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        ),
        PokemonEntity(
            "#002",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "Ivysaur",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        , PokemonEntity(
            "#003",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "Venusaur",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
    )

    @After
    fun endTest() {
        appDatabase.close()
        stopKoin()
    }
}