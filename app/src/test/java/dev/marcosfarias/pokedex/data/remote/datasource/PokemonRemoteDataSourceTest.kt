package dev.marcosfarias.pokedex.data.remote.datasource

import com.google.gson.Gson
import dev.marcosfarias.pokedex.data.remote.api.PokemonService
import dev.marcosfarias.pokedex.getJson
import dev.marcosfarias.pokedex.utils.empty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.HttpURLConnection

class PokemonRemoteDataSourceTest {
    private val mockWebServer = MockWebServer()

    private lateinit var remoteDataSource: PokemonRemoteDataSource

    @Before
    fun setup() {
        val dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/pokemon.json" -> MockResponse()
                        .setResponseCode(HttpURLConnection.HTTP_OK)
                        .setBody(getJson("json/pokemon/pokemons.json"))
                    else -> MockResponse().setResponseCode(404)
                }
            }
        }

        mockWebServer.dispatcher = dispatcher
        mockWebServer.start()

        val pokemonService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonService::class.java)

        remoteDataSource = PokemonRemoteDataSource(pokemonService)
    }

    @Test
    fun getPokemons_returnPokemonsResponseHttpOk() = runBlocking(Dispatchers.IO) {
        val pokemonsResponse = remoteDataSource.getPokemons()

        assertThat(pokemonsResponse.code(), `is`(HttpURLConnection.HTTP_OK))
        assertThat(pokemonsResponse.body(), `is`(not(nullValue())))
        assertThat(pokemonsResponse.body()!![0].id, `is`("#001"))
    }

    @After
    fun endTest() {
        mockWebServer.shutdown()
    }

}