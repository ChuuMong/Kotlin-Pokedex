package dev.marcosfarias.pokedex.ui.dashboard.about

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.databinding.FragmentAboutBinding
import dev.marcosfarias.pokedex.ui.BaseFragment
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_about.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutFragment : BaseFragment<FragmentAboutBinding>() {

    companion object {
        private const val TAG = "AboutFragment"

        @JvmStatic
        fun newInstance(id: String?) = AboutFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun getLayoutId() = R.layout.fragment_about

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
