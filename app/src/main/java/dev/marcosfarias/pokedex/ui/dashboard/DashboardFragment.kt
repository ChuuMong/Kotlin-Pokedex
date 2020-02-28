package dev.marcosfarias.pokedex.ui.dashboard

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.databinding.FragmentDashboardBinding
import dev.marcosfarias.pokedex.ui.BaseFragment
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {
    private val TAG = "DashboardFragment"

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun getLayoutId() = R.layout.fragment_dashboard

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dashboardViewModel = dashboardViewModel

        dashboardViewModel.resultError.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
            Log.e(TAG, it.message, it)
        })

        dashboardViewModel.pokemon.observe(viewLifecycleOwner, Observer {
            val color = PokemonColorUtil(view.context).getPokemonColor(it.typeOfPokemon)
            requireActivity().window.statusBarColor = color
        })

        val id = checkNotNull(arguments?.getString("id"))
        dashboardViewModel.getPokemonById(id)

        binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager, requireActivity(), id)
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}
