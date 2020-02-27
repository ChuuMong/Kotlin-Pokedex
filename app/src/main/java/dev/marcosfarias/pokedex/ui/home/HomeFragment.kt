package dev.marcosfarias.pokedex.ui.home

import android.os.Bundle
import android.view.View
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.databinding.FragmentHomeBinding
import dev.marcosfarias.pokedex.ui.BaseFragment
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun getLayoutId() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor =
            PokemonColorUtil(view.context).convertColor(R.color.red)

        binding.homeViewModel = homeViewModel
    }
}
