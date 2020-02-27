package dev.marcosfarias.pokedex.ui.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marcosfarias.pokedex.data.model.Pokemon
import dev.marcosfarias.pokedex.data.model.Result
import dev.marcosfarias.pokedex.data.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokedexViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _pokemons = MutableLiveData<List<Pokemon>>()
    val pokemons: LiveData<List<Pokemon>> get() = _pokemons

    private val _resultError = MutableLiveData<Exception>()
    val resultError: LiveData<Exception> get() = _resultError

    fun getPokemons() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = pokemonRepository.getPockmons()

            withContext(Dispatchers.Main) {
                when (result) {
                    is Result.Success -> _pokemons.value = result.data
                    is Result.Error -> _resultError.value = result.exception
                }
                _isLoading.value = false
            }
        }
    }
}
