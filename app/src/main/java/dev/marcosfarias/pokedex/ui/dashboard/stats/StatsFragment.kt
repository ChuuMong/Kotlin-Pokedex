package dev.marcosfarias.pokedex.ui.dashboard.stats

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.databinding.FragmentStatsBinding
import dev.marcosfarias.pokedex.ui.BaseFragment
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import dev.marcosfarias.pokedex.ui.dashboard.about.AboutFragment
import kotlinx.android.synthetic.main.fragment_stats.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatsFragment : BaseFragment<FragmentStatsBinding>() {

    companion object {
        private const val TAG = "StatsFragment"

        @JvmStatic
        fun newInstance(id: String?) = StatsFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun getLayoutId() = R.layout.fragment_stats

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = checkNotNull(arguments?.getString("id"))

        binding.dashboardViewModel = dashboardViewModel

        dashboardViewModel.resultError.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
            Log.e(TAG, it.message, it)
        })

        dashboardViewModel.getPokemonById(id)
    }
}
