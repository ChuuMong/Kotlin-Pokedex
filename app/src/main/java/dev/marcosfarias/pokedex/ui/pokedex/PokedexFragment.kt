package dev.marcosfarias.pokedex.ui.pokedex

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.databinding.FragmentPokedexBinding
import dev.marcosfarias.pokedex.ui.BaseFragment
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexFragment : BaseFragment<FragmentPokedexBinding>() {
    companion object {
        private val TAG = "PokedexFragment"
    }

    private val pokedexViewModel: PokedexViewModel by viewModel()

    override fun getLayoutId() = R.layout.fragment_pokedex

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pokedexViewModel = pokedexViewModel

        requireActivity().window.statusBarColor =
            PokemonColorUtil(view.context).convertColor(R.color.background)

        pokedexViewModel.resultError.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, it.message, it)
        })

        pokedexViewModel.getPokemons()
    }
}
