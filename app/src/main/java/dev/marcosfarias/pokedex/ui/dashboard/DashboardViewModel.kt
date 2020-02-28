package dev.marcosfarias.pokedex.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marcosfarias.pokedex.data.local.dao.PokemonDAO
import dev.marcosfarias.pokedex.data.local.entity.PokemonEntity
import dev.marcosfarias.pokedex.data.model.Pokemon
import dev.marcosfarias.pokedex.data.model.Result
import dev.marcosfarias.pokedex.data.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> get() = _pokemon

    private val _resultError = MutableLiveData<Exception>()
    val resultError: LiveData<Exception> get() = _resultError

    fun getPokemonById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = pokemonRepository.getPokemonById(id)

            withContext(Dispatchers.Main) {
                when (result) {
                    is Result.Success -> _pokemon.value = result.data
                    is Result.Error -> _resultError.value = result.exception
                }
            }
        }
    }
}
