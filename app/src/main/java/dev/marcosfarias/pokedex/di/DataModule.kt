package dev.marcosfarias.pokedex.di

import dev.marcosfarias.pokedex.data.local.datasource.PokemonLocalDataSource
import dev.marcosfarias.pokedex.data.remote.datasource.PokemonRemoteDataSource
import dev.marcosfarias.pokedex.data.repository.HomeRepository
import dev.marcosfarias.pokedex.data.repository.PokemonRepository
import dev.marcosfarias.pokedex.data.repository.mapper.PokemonMapper
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val homeModel = module {
    factory { HomeRepository() }
}

val pokemonModule = module {
    single { PokemonRepository(get(), get(), get()) }

    factory { PokemonLocalDataSource(get()) }
    factory { PokemonRemoteDataSource(get()) }
    factory { PokemonMapper() }
}

