package dev.marcosfarias.pokedex.di

import dev.marcosfarias.pokedex.data.repository.HomeRepository
import org.koin.dsl.module

val dataModule = module {
    factory { HomeRepository() }
}