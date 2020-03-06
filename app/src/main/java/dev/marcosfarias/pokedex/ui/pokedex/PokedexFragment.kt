package dev.marcosfarias.pokedex.ui.pokedex

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.leinardi.android.speeddial.SpeedDialView
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.databinding.FragmentPokedexBinding
import dev.marcosfarias.pokedex.ui.BaseFragment
import dev.marcosfarias.pokedex.ui.generation.GenerationFragment
import dev.marcosfarias.pokedex.ui.search.SearchFragment
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexFragment : BaseFragment<FragmentPokedexBinding>() {
    companion object {
        private val TAG = "PokedexFragment"
        private const val CURRENT_POSITION = "CURRENT_POSITION"
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
            Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
        })

        binding.speedDial.inflate(R.menu.menu_pokedex)
        binding.speedDial.setOnActionSelectedListener(SpeedDialView.OnActionSelectedListener { item ->
            when (item.id) {
                R.id.menuAllGen -> showAllGen()
                R.id.menuSearch -> showSearch()
            }
            binding.speedDial.close()
            return@OnActionSelectedListener true
        })

        pokedexViewModel.getPokemons()
    }

    private fun showAllGen() {
        val dialog = GenerationFragment()
        dialog.show(childFragmentManager, "")
    }

    private fun showSearch() {
        val dialog = SearchFragment()
        dialog.show(childFragmentManager, "")
    }
}
