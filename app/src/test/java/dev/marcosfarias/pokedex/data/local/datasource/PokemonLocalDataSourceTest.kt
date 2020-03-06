package dev.marcosfarias.pokedex.data.local.datasource

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marcosfarias.pokedex.data.local.AppDatabase
import dev.marcosfarias.pokedex.data.local.entity.PokemonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
class PokemonLocalDataSourceTest {

    private lateinit var localDataSource: PokemonLocalDataSource
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()

        localDataSource = PokemonLocalDataSource(appDatabase.pokemonDAO())
    }

    @Test
    fun addAllPokemons_returnAllPokemons() = runBlocking(Dispatchers.IO) {
        localDataSource.addAll(getTestPokemonEntities())

        val pokemons = localDataSource.getPokemons()
        assertThat(pokemons[0].id, `is`("#001"))
        assertThat(pokemons[1].id, `is`("#002"))
        assertThat(pokemons[2].id, `is`(not("#002")))
    }

    @Test
    fun addAllPokemons_returnPokemonById() = runBlocking(Dispatchers.IO) {
        localDataSource.addAll(getTestPokemonEntities())

        val pokemon = localDataSource.getPokemonById("#001")
        assertThat(pokemon.name, `is`("Bulbasaur"))
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