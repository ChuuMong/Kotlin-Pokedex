package dev.marcosfarias.pokedex.data.repository

import dev.marcosfarias.pokedex.data.local.datasource.PokemonLocalDataSource
import dev.marcosfarias.pokedex.data.local.entity.PokemonEntity
import dev.marcosfarias.pokedex.data.model.Pokemon
import dev.marcosfarias.pokedex.data.model.Result
import dev.marcosfarias.pokedex.data.remote.datasource.PokemonRemoteDataSource
import dev.marcosfarias.pokedex.data.repository.mapper.PokemonMapper
import dev.marcosfarias.pokedex.getPokemonFromJson
import dev.marcosfarias.pokedex.toPokemonEntity
import dev.marcosfarias.pokedex.toPokemonResponse
import dev.marcosfarias.pokedex.utils.empty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert.assertThat
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class PokemonRepositoryTest {

    @Mock
    private lateinit var remoteDataSource: PokemonRemoteDataSource

    @Mock
    private lateinit var localDataSource: PokemonLocalDataSource

    private lateinit var repository: PokemonRepository

    private val mapper = PokemonMapper()

    @Before
    fun setup() {
        repository = PokemonRepository(localDataSource, remoteDataSource, mapper)
    }

    @Test
    fun getPockmons_returnLocalDataSourceResultPokemons() = runBlocking(Dispatchers.IO) {
        given(localDataSource.getPokemons()).willReturn(getPokemonFromJson().map(toPokemonEntity()))

        val result = repository.getPockmons()
        assertThat(result, IsInstanceOf(Result.Success::class.java))
        assertThat((result as Result.Success).data[0], IsInstanceOf(Pokemon::class.java))
        assertThat(result.data[0].id, `is`("#001"))
    }

    @Test
    fun getPokemons_returnRemoteDataSourceResultPokemons() = runBlocking(Dispatchers.IO) {
        given(localDataSource.getPokemons()).willReturn(emptyList())
        given(remoteDataSource.getPokemons())
            .willReturn(Response.success(getPokemonFromJson().map(toPokemonResponse())))

        val result = repository.getPockmons()

        assertThat(result, IsInstanceOf(Result.Success::class.java))
        assertThat((result as Result.Success).data[0], IsInstanceOf(Pokemon::class.java))
        assertThat(result.data[0].id, `is`("#001"))
    }

    @Test
    fun getPokemons_returnRemoteDataSourceResultError() = runBlocking(Dispatchers.IO) {
        given(localDataSource.getPokemons()).willReturn(emptyList())
        given(remoteDataSource.getPokemons()).willReturn(Response.error(404, "{}".toResponseBody()))

        val result = repository.getPockmons()

        assertThat(result, IsInstanceOf(Result.Error::class.java))
    }

    @Test
    fun getPokemonById_returnLocalDataSourceGetPokemonById_toPokemon() {
        val id = "#001"
        given(localDataSource.getPokemonById(id)).willReturn(
            getPokemonFromJson().map(
                toPokemonEntity()
            ).first { it.id == id })

        val result = repository.getPokemonById(id)
        assertThat(result, IsInstanceOf(Result.Success::class.java))
        assertThat((result as Result.Success).data, IsInstanceOf(Pokemon::class.java))
        assertThat(result.data.name, `is`("Bulbasaur"))
    }
}