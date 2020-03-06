package dev.marcosfarias.pokedex.data.local.datasource

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marcosfarias.pokedex.data.local.AppDatabase
import dev.marcosfarias.pokedex.data.local.entity.PokemonEntity
import dev.marcosfarias.pokedex.getPokemonFromJson
import dev.marcosfarias.pokedex.toPokemonEntity
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
        localDataSource.addAll(getPokemonFromJson().map(toPokemonEntity()))

        val pokemons = localDataSource.getPokemons()
        assertThat(pokemons[0].id, `is`("#001"))
        assertThat(pokemons[1].id, `is`("#002"))
        assertThat(pokemons[2].id, `is`(not("#002")))
    }

    @Test
    fun addAllPokemons_returnPokemonById() = runBlocking(Dispatchers.IO) {
        localDataSource.addAll(getPokemonFromJson().map(toPokemonEntity()))

        val pokemon = localDataSource.getPokemonById("#001")
        assertThat(pokemon.name, `is`("Bulbasaur"))
    }

    @After
    fun endTest() {
        appDatabase.close()
        stopKoin()
    }
}